<template>
    <div class="comment">
        <u-comment :config="config" upload @submit="submit" @like="like" relative-time style="width: 100%;">
            <!-- <div>导航栏卡槽</div> -->
            <!-- <template #info>用户信息卡槽</template> -->
            <template #header style="display: none !important;">评论</template>
        </u-comment>

    </div>
</template>
<script setup lang="ts">
import { ref, reactive, onMounted, defineProps } from "vue"
import emoji from '@/utils/emoji'
import { type CommentApi, type ConfigApi, type SubmitParamApi, UToast, dayjs, createObjectURL } from 'undraw-ui'
import { getComments } from '@/api/activity/activity'

const props = defineProps({
    actId: Number,
})

const actId = ref(Number(props.actId));
interface CommentsInfo {
    commentId: number;
    activityId: number;
    commentatorId: number;
    parentId: number;
    commentContent: string;
    childrenComments: any[];

    publishTime: string;
    commentatorName: string;
    parentCommentatorName: string;
}
const commentsInfo = ref<CommentsInfo | null>(null);

const getCommentsInfo = async () => {
    try {
        const result = await getComments(actId.value);
        commentsInfo.value = result.data;
        console.log('6', result.data);


    } catch (error) {
        console.error('Error fetching data:');
    } finally {

    }
};

onMounted(() => {
    console.log(props);
    // getCommentsInfo();
})


const config = reactive<ConfigApi>({
    user: {
        id: 1,
        username: 'jack',
        avatar: 'https://static.juzicon.com/avatars/avatar-200602130320-HMR2.jpeg?x-oss-process=image/resize,w_100',
        // 评论id数组 建议:存储方式用户uid和评论id组成关系,根据用户uid来获取对应点赞评论id,然后加入到数组中返回
        likeIds: [1, 2, 3]
    },
    emoji: emoji,
    comments: [],
    total: 10
})




let temp_id = 100
// 提交评论事件
const submit = ({ content, parentId, files, finish }: SubmitParamApi) => {
    console.log('提交评论: ' + content, parentId, files)

    /**
     * 上传文件后端返回图片访问地址，格式以'||'为分割; 如:  '/static/img/program.gif||/static/img/normal.webp'
     */
    let contentImg = files?.map(e => createObjectURL(e)).join('||')
    temp_id += 1
    const comment: CommentApi = {
        id: String(temp_id),
        parentId: parentId,
        uid: config.user.id,
        address: '来自江苏',
        content: content,
        likes: 1,
        createTime: dayjs().subtract(5, 'seconds').toString(),
        contentImg: contentImg,
        user: {
            username: config.user.username,
            avatar: config.user.avatar,
            level: 6,
            homeLink: `/${temp_id}`
        },
        reply: null
    }
    setTimeout(() => {
        finish(comment)
        UToast({ message: '评论成功!', type: 'info' })
    }, 200)
}
// 点赞按钮事件 将评论id返回后端判断是否点赞，然后在处理点赞状态
const like = (id: string, finish: () => void) => {
    console.log('点赞: ' + id)
    setTimeout(() => {
        finish()
    }, 200)
}

// 已有评论
config.comments = [
    {
        id: '2',
        parentId: null,
        uid: '2',
        address: '来自苏州',
        content: '长风破浪会有时，直挂云帆济沧海。',
        likes: 11,
        createTime: dayjs().subtract(10, 'day').toString(),
        user: {
            username: '悟二空',
            avatar: 'https://static.juzicon.com/user/avatar-bf22291e-ea5c-4280-850d-88bc288fcf5d-220408002256-ZBQQ.jpeg',
            level: 1,
            homeLink: '/2'
        }
    }
]


</script>
<style scoped></style>
