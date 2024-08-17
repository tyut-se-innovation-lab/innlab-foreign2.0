<template>
    <div class="homeHistory">
        <div class="container">
            <div class="descTitle">
                <h2>发展历程</h2>
                <span>HISTORY</span>
            </div>
            <div class="body-wrap">

                <div class="pres-timeline" id="this-timeline">

                    <div class="periods-container">
                        <section v-for="(item, index) in historyList" class="period-single"
                            :class="{ 'active': index === activePeriodIndex, 'prev': index === activePeriodIndex - 1, 'next': index === activePeriodIndex + 1 }"
                            :period="index" @click="setActivePeriod(index)">
                            <h4 class="year"> {{ item.historyTime }}</h4>
                            <h2 class="title"> {{ item.historyTitle }}</h2>

                        </section>

                        <div class="btn-back" @click="prevPeriod"></div>
                        <div class="btn-next" @click="nextPeriod"></div>
                    </div>


                    <div class="timeline-container">

                        <div class="timeline"></div>

                        <div class="btn-back" @click="prevPeriod"><svg width="30" height="30" viewBox="0 0 30 30"
                                xmlns="http://www.w3.org/2000/svg">
                                <path fill="none" d="M0 0h30v30H0z" />
                                <path fill="#D8D8D8" fill-rule="evenodd"
                                    d="M11.828 15l7.89-7.89-2.83-2.828L6.283 14.89l.11.11-.11.11L16.89 25.72l2.828-2.83" />
                            </svg></div>
                        <div class="btn-next" @click="nextPeriod"><svg width="30" height="30" viewBox="0 0 30 30"
                                xmlns="http://www.w3.org/2000/svg">
                                <path fill="none" d="M0 0h30v30H0z" />
                                <path fill="#D8D8D8" fill-rule="evenodd"
                                    d="M18.172 14.718l-7.89-7.89L13.112 4l10.606 10.607-.11.11.11.11-10.608 10.61-2.828-2.83 7.89-7.89" />
                            </svg></div>
                    </div>

                    <div class="cards-container">
                        <section v-for="(item, index) in historyList" class="card-single" :period="index"
                            :class="{ 'active': index === activeCardIndex }" @click="setActiveCard(index)">
                            <h4 class="year"> {{ item.historyTime }}</h4>
                            <h2 class="title">{{ item.historyTitle }}</h2>
                            <div class="content">
                                <el-image :src="item.headerImage" preview-teleported :zoom-rate="1.2" :max-scale="7"
                                    :min-scale="0.2" :preview-src-list="[item.headerImage]" fit="cover" />
                                <p> {{ item.historyContent }}</p>
                            </div>
                        </section>


                    </div>

                </div>

            </div>


        </div>



    </div>
</template>

<script setup lang="ts">
import { ref, reactive, onMounted } from "vue"
import { getHistoryList } from '@/api/history/history.ts';
import { parseLanzouLink } from '@/utils/getFileByBackend';
import AOS from 'aos'
import 'aos/dist/aos.css'
import $ from 'jquery';

const historyList = ref<Array<{ historyTime: string; historyTitle: string; historyContent: string }>>([]);
historyList.value = [
    { historyTime: '2000', historyTitle: 'Title 1', historyContent: 'Content 1Content 1' },
    { historyTime: '2001', historyTitle: 'Title 2', historyContent: 'Content 2' },
    { historyTime: '2002', historyTitle: 'Title 3', historyContent: 'Content 3' },
    { historyTime: '2000', historyTitle: 'Title 1', historyContent: 'Content 1' },
    { historyTime: '2001', historyTitle: 'Title 2', historyContent: 'Content 2' },
    { historyTime: '2002', historyTitle: 'Title 3', historyContent: 'Content 3' },
    { historyTime: '2000', historyTitle: 'Title 1', historyContent: 'Content 1' },
    { historyTime: '2001', historyTitle: 'Title 2', historyContent: 'Content 2' },
    { historyTime: '2001', historyTitle: 'Title 2', historyContent: 'Content 2' },
    { historyTime: '2002', historyTitle: 'Title 3', historyContent: 'Content 3' },

];

const getHistorys = async () => {
    try {
        const result = await getHistoryList();
        if (result.code == 200) {
            historyList.value = result.data.records;
            historyList.value.forEach(async e => {
                e.headerImage = await parseLanzouLink(e.headerImage);
            })
        }

    } catch (error) {
        console.error('Error fetching data:');
    } finally {

    }
};

const activePeriodIndex = ref(0);
const activeCardIndex = ref(0);
const setActivePeriod = (index: number) => {
    activePeriodIndex.value = index;
    activeCardIndex.value = index; // Ensure card index matches period index
};

const setActiveCard = (index: number) => {
    activeCardIndex.value = index;
    activePeriodIndex.value = index; // Ensure period index matches card index
};

const prevPeriod = () => {
    if (activePeriodIndex.value > 0) {
        activePeriodIndex.value -= 1;
        activeCardIndex.value = activePeriodIndex.value; // Sync card index
    }
};

const nextPeriod = () => {
    if (activePeriodIndex.value < historyList.value.length - 1) {
        activePeriodIndex.value += 1;
        activeCardIndex.value = activePeriodIndex.value; // Sync card index
    }
};

const prevCard = () => {
    if (activeCardIndex.value > 0) {
        activeCardIndex.value -= 1;
        activePeriodIndex.value = activeCardIndex.value; // Sync period index
    }
};

const nextCard = () => {
    if (activeCardIndex.value < historyList.value.length - 1) {
        activeCardIndex.value += 1;
        activePeriodIndex.value = activeCardIndex.value; // Sync period index
    }
};



class PRESTimeline {
    private base: JQuery<HTMLElement>;
    private color: { [key: string]: string };
    private periodContainer: JQuery<HTMLElement>;
    private cardContainer: JQuery<HTMLElement>;
    private timelineNodeContainer: JQuery<HTMLElement>;
    private periodData: JQuery<HTMLElement>;
    private cardData: JQuery<HTMLElement>;
    private activePeriod: JQuery<HTMLElement>;
    private activePeriodIndex: number;
    private activeCard: JQuery<HTMLElement>;
    private activeCardIndex: number;
    private timelineData: JQuery<HTMLElement>;

    constructor(target: JQuery<HTMLElement>, color: { [key: string]: string }) {
        this.base = target;
        this.color = color;
        this.periodContainer = $(this.base).find('.periods-container');
        this.cardContainer = $(this.base).find('.cards-container');
        this.timelineNodeContainer = $(this.base).find('.timeline-container .timeline');
        this._parseData();
        this._initialColor();
        this._generateTimeline();
        this._setStateClasses();
        this._assignBtn();
        this._adjustPeriodContainer();
        this._adjustCardContainer();
    }
    private _parseData() {
        const base = this.base;
        const periods = $(base).find('.periods-container section');
        periods.each((index, section) => {
            const periodAttr = $(section).attr('period');
            if (periodAttr) {
                (section as any).period = periodAttr;
            }
            (section as any).index = index;
        });
        this.periodData = periods;

        const data = $(base).find('.cards-container section');
        data.each((index, section) => {
            const periodAttr = $(section).attr('period');
            if (periodAttr) {
                (section as any).period = periodAttr;
            }
            (section as any).index = index;
        });
        this.cardData = data;

        this.activePeriod = this.periodData.first();
        this.activePeriodIndex = 0;
        this.activeCard = this.cardData.first();
        this.activeCardIndex = 0;
    }


    private _setStateClasses() {
        $(this.base).find('.periods-container section.active').removeClass('active');
        $(this.base).find('.periods-container section.prev').removeClass('prev');
        $(this.base).find('.periods-container section.next').removeClass('next');
        $(this.activePeriod).addClass('active');

        if ($(this.activePeriod).prev().length > 0) {
            $(this.activePeriod).prev().addClass('prev');
            $(this.base).find('.periods-container .btn-back').removeClass('hide');
        } else {
            $(this.base).find('.periods-container .btn-back').addClass('hide');
        }
        if ($(this.activePeriod).next().length > 0) {
            $(this.activePeriod).next().addClass('next');
            $(this.base).find('.periods-container .btn-next').removeClass('hide');
        } else {
            $(this.base).find('.periods-container .btn-next').addClass('hide');
        }

        $(this.base).find('.cards-container section.active').removeClass('active');
        $(this.base).find('.cards-container section.prev').removeClass('prev');
        $(this.base).find('.cards-container section.next').removeClass('next');
        $(this.activeCard).addClass('active');

        if ($(this.activeCard).prev().length > 0) {
            $(this.activeCard).prev().addClass('prev');
        }
        if ($(this.activeCard).next().length > 0) {
            $(this.activeCard).next().addClass('next');
        }

        $(this.base).find('.timeline li.active').removeClass('active');
        $(this.timelineData.eq(this.activeCardIndex)).addClass('active');

        const timelineB = $(this.base).find('.timeline-container .btn-back');
        const timelineN = $(this.base).find('.timeline-container .btn-next');

        if (this.activeCardIndex === 0) {
            timelineB.addClass('hide');
        } else {
            timelineB.removeClass('hide');
        }
        if (this.activeCardIndex >= this.cardData.length - 1) {
            timelineN.addClass('hide');
        } else {
            timelineN.removeClass('hide');
        }
    }

    private _generateTimeline() {
        const htmlWrap = '<ol></ol>';
        $(this.timelineNodeContainer).append(htmlWrap);
        const wrap = $(this.timelineNodeContainer).find('ol');
        const numNode = this.cardData.length;

        for (let i = 0; i < numNode; i++) {
            const color = (this.cardData.eq(i) as any).color;
            const period = (this.cardData.eq(i) as any).period;
            wrap.append(`<li class="${period}" style="border-color: ${color}"></li>`);
        }

        const nodeW = 200;
        wrap.css('width', nodeW * numNode - 16);
        this.timelineData = $(this.base).find('.timeline ol li');
    }

    private _assignBtn() {
        const periodPrev = $(this.base).find('.periods-container .btn-back');
        const periodNext = $(this.base).find('.periods-container .btn-next');

        periodPrev.on('click', () => {
            if (this.activePeriodIndex > 0) {
                this.activePeriodIndex -= 1;
                this.activePeriod = this.periodData.eq(this.activePeriodIndex);
                this._chainActions('period');
                this._setStateClasses();
            }
            this._adjustPeriodContainer();
        });

        periodNext.on('click', () => {
            if (this.activePeriodIndex < this.periodData.length - 1) {
                this.activePeriodIndex += 1;
                this.activePeriod = this.periodData.eq(this.activePeriodIndex);
                this._chainActions('period');
                this._setStateClasses();
            }
            this._adjustPeriodContainer();
        });

        const timelinePrev = $(this.base).find('.timeline-container .btn-back');
        const timelineNext = $(this.base).find('.timeline-container .btn-next');

        timelinePrev.on('click', () => {
            if (this.activeCardIndex > 0) {
                this.activeCardIndex -= 1;
                this.activeCard = this.cardData.eq(this.activeCardIndex);
                this._chainActions('timeline');
                this._setStateClasses();
            }
            this._adjustCardContainer();
            this._adjustPeriodContainer();
        });

        timelineNext.on('click', () => {
            if (this.activeCardIndex < this.cardData.length - 1) {
                this.activeCardIndex += 1;
                this.activeCard = this.cardData.eq(this.activeCardIndex);
                this._chainActions('timeline');
                this._setStateClasses();
            }
            this._adjustCardContainer();
            this._adjustPeriodContainer();
        });

        this.timelineData.each((index, element) => {
            $(element).on('click', () => {
                this.activeCardIndex = this.cardData.eq(index).index();
                this.activeCard = this.cardData.eq(this.activeCardIndex);
                this._chainActions('timeline');
                this._setStateClasses();
                this._adjustCardContainer();
                this._shiftTimeline();
            });
        });
    }

    private _initialColor() {
        this.periodData.each((index, period) => {
            const periodName = $(period).attr('period')!;
            (period as any).color = this.color[periodName];
            $(period).css('border-color', (period as any).color);
            $(period).find('.year').css('color', (period as any).color);

            const styleElement = document.createElement('style');
            document.head.appendChild(styleElement);
            styleElement.sheet!.insertRule(`li.${periodName}.active { background-color: ${this.color[periodName]} !important }`, 0);
            styleElement.sheet!.insertRule(`li.${periodName}::before { background-color: ${this.color[periodName]} }`, 0);
            styleElement.sheet!.insertRule(`li.${periodName}::after { background-color: ${this.color[periodName]} }`, 0);
        });

        this.cardData.each((index, card) => {
            const periodName = $(card).attr('period')!;
            (card as any).color = this.color[periodName];
            $(card).css('border-color', (card as any).color);
            $(card).find('.year').css('color', (card as any).color);
        });
    }

    private _adjustPeriodContainer() {
        const activeHeight = $(this.activePeriod).outerHeight();
        $(this.periodContainer).height(activeHeight);
        console.log('top adjusted');
    }

    private _adjustCardContainer() {
        const activeHeight = $(this.activeCard).outerHeight() + 24;
        $(this.cardContainer).height(activeHeight);
        console.log('bot adjusted');
    }

    private _shiftTimeline() {
        const timelineWidth = $(this.base).find('.timeline-container').outerWidth();
        const timelinePadding = 210;
        const activeNodeX = $(this.timelineData.eq(this.activeCardIndex)).position().left;
        const finalPosition = -activeNodeX + timelinePadding;
        $(this.timelineNodeContainer).css('left', finalPosition);
        console.log(activeNodeX);
    }

    private _chainActions(state: string) {
        switch (state) {
            case 'period':
                console.log('period');
                if (this.activePeriod.attr('period') !== this.activeCard.attr('period')) {
                    const filteredCards = this.cardData.filter((index, card) => $(card).attr('period') === this.activePeriod.attr('period'));
                    if (filteredCards.length > 0) {
                        this.activeCard = filteredCards.first();
                        this.activeCardIndex = this.activeCard.index();
                    }
                }
                break;
            case 'timeline':
                console.log('timeline');
                if (this.activeCard.attr('period') !== this.activePeriod.attr('period')) {
                    const matchingPeriod = this.periodData.filter((index, period) => $(period).attr('period') === this.activeCard.attr('period'));
                    if (matchingPeriod.length > 0) {
                        this.activePeriod = matchingPeriod.first();
                        this.activePeriodIndex = this.activePeriod.index();
                    }
                }
                break;
        }
        this._shiftTimeline();
        this._adjustCardContainer();
    }
}
// ## document load ##
$(document).ready(() => {
    const colorcode: { [key: string]: string } = {
        'period1': '#fec541',
        'period2': '#36d484',
        'period3': '#32ccf4'
    };
    new PRESTimeline($('#this-timeline'), colorcode);
});




onMounted(() => {
    AOS.init();
    getHistorys();

})

</script>

<style scoped>
.homeHistory {
    width: 100%;
    background-color: #e5e5e5;
    /* height: 600px; */
    /* padding: 0 120px; */

}

.container {
    width: 100%;
    height: 100%;
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    overflow: hidden;
}

.descTitle {
    /* border-left: 3px solid #729bba; */
    padding: 0 4%;
    margin: 4%;
    display: flex;
    flex-direction: column;
    align-items: center;
}

.descTitle h2 {
    font-size: 42px;
    color: #333;
    font-weight: bold;
    margin-top: 0;
}

.descTitle span {
    font-size: 16px;
    color: #333;
    margin-top: 10px;
}

.body-wrap {
    background-color: #fff;
    width: 100%;
    min-height: 500px;
    margin: 0 auto;
    font-size: 12px;
}

.pres-timeline {
    font-family: roboto, helvetica, sans-serif;
    font-size: 12px;
    color: #4A4A4A;
    width: 100%;
    margin: 30px 0;
}

.pres-timeline>div>div {
    padding: 1em 0;
    box-sizing: border-box;
}

.pres-timeline .periods-container {
    overflow: hidden;
    box-sizing: border-box;
    position: relative;
    min-height: 12em;
    transition: height 0.5s ease-in-out;
    background-color: #FFF;
}

.cards-container {
    overflow: hidden;
    box-sizing: border-box;
    position: relative;
    min-height: 60em;
    transition: height 0.5s ease-in-out;
    background-color: #FFF;
}

.periods-container:before {
    background-image: linear-gradient(left, #FFF, rgba(248, 248, 248, 0));
    left: 0;
    content: "";
    position: absolute;
    z-index: 2;
    top: 0;
    height: 100%;
    width: 100px;
}

.periods-container:after {
    background-image: linear-gradient(right, #FFF, rgba(248, 248, 248, 0));
    right: 0;
    content: "";
    position: absolute;
    z-index: 2;
    top: 0;
    height: 100%;
    width: 100px;
}

.periods-container .btn-back,
.periods-container .btn-next {
    display: inline-block;
    width: 15%;
    height: 100%;
    position: absolute;
    cursor: pointer;
    z-index: 10;
    transition: 0.3s ease-in-out;
}

.periods-container .btn-back:hover,
.periods-container .btn-next:hover {
    background-color: rgba(0, 0, 0, 0.05);
}

.periods-container .btn-back.hide,
.periods-container .btn-next.hide {
    display: none;
}

.periods-container .btn-back {
    left: 0;
}

.periods-container .btn-next {
    right: 0;
}

.periods-container section {
    width: 70%;
    height: 0;
    position: absolute;
    margin-left: 15%;
    border-bottom: 5px solid #dfe3e6;
    padding: 1.5em;
    box-sizing: border-box;
    transition: transform 0.3s ease-in-out, opacity 0.2s ease, height 0.3s ease;
    bottom: 0;
    opacity: 0;
    background-color: #fff;
}

.periods-container section.active {
    height: auto;
    opacity: 1;
    transform: translateX(0);
    z-index: 5;
}

.periods-container section.active .title,
.periods-container section.active p {
    display: block;
}

.periods-container section.prev {
    height: auto;
    opacity: 0.4;
    transform: translateX(-100%);
    z-index: 0;
}

.periods-container section.prev .year {
    text-align: right;
}

.periods-container section.next {
    height: auto;
    opacity: 0.4;
    transform: translateX(100%);
    z-index: 0;
}

.periods-container section .year {
    font-size: 20px;
    font-weight: 400;
}

.periods-container section .title {
    color: #4A4A4A;
    font-size: 2em;
    font-weight: 400;
    display: none;
}

.periods-container section p {
    display: none;
}

.timeline-container {
    position: relative;
    width: 100%;
    height: 50px;
    overflow: hidden;
}

.timeline-container:before {
    background-image: linear-gradient(left, #FFF, rgba(248, 248, 248, 0));
    left: 0;
    content: "";
    position: absolute;
    z-index: 2;
    top: 0;
    height: 100%;
    width: 100px;
}

.timeline-container:after {
    background-image: linear-gradient(right, #FFF, rgba(248, 248, 248, 0));
    right: 0;
    content: "";
    position: absolute;
    z-index: 2;
    top: 0;
    height: 100%;
    width: 100px;
}

.timeline-container .timeline {
    position: absolute;
    display: block;
    height: 50px;
    transition: left 0.3s ease-in-out;
}

.timeline-container .timeline ol {
    display: block;
    width: 100%;
    height: 2px;
    background-color: #dfe3e6;
    list-style: none;
    padding-left: 210px;
    padding-right: 300px;
}

.timeline-container .timeline ol li {
    display: inline-block;
    padding: 5px;
    margin-top: -11px;
    margin-left: 80px;
    border-radius: 50%;
    border: 3px solid #7f9298;
    background-color: #FFF;
    position: relative;
    cursor: pointer;
    box-shadow: 0 2px 5px rgba(0, 0, 0, 0.2);
}

.timeline-container .timeline ol li.active {
    box-shadow: none;
}

.timeline-container .timeline ol li.active:before {
    content: "";
    display: block;
    height: 25px;
    width: 1px;
    position: absolute;
    top: -25px;
    transition: opacity 0.3s ease-in-out;
}

.timeline-container .timeline ol li.active:after {
    content: "";
    display: block;
    height: 25px;
    width: 1px;
    position: absolute;
    bottom: -25px;
    transition: opacity 0.3s ease-in-out;
}

.timeline-container .btn-back,
.timeline-container .btn-next {
    display: inline-block;
    position: absolute;
    cursor: pointer;
    margin-top: -2px;
    z-index: 11;
    transition: all 0.3s ease;
}

.timeline-container .btn-back.hide,
.timeline-container .btn-next.hide {
    display: none;
}

.timeline-container .btn-back:hover,
.timeline-container .btn-next:hover {
    border-color: #7f9298;
}

.timeline-container .btn-back {
    left: 1em;
}

.timeline-container .btn-next {
    right: 1em;
}

.cards-container:before {
    background-image: linear-gradient(left, #FFF, rgba(248, 248, 248, 0));
    left: 0;
    content: "";
    position: absolute;
    z-index: 2;
    top: 0;
    height: 100%;
    width: 100px;
}

.cards-container:after {
    background-image: linear-gradient(right, #FFF, rgba(248, 248, 248, 0));
    right: 0;
    content: "";
    position: absolute;
    z-index: 2;
    top: 0;
    height: 100%;
    width: 100px;
}

.cards-container section {
    width: 70%;
    min-height: 45em;
    position: absolute;
    margin-left: 15%;
    margin-bottom: 2em;
    border: 1px solid #f5f7f6;
    padding: 1.5em;
    box-sizing: border-box;
    transition: transform 0.3s ease-in-out;
    top: 0;
    opacity: 0;
    border-radius: 8px;
    background-color: #fff;
    box-shadow: 0 10px 15px rgba(0, 0, 0, 0.15);
}

.cards-container section.active {
    /* height: auto; */
    opacity: 1;
    transform: translateX(0);
    z-index: 5;
}

.cards-container section.prev {
    height: auto;
    opacity: 0.4;
    transform: translateX(-105%);
    z-index: 0;
}

.cards-container section.next {
    height: auto;
    opacity: 0.4;
    transform: translateX(105%);
    z-index: 0;
}

.cards-container section .year {
    text-align: center;
    font-size: 16px;
    margin: 0;
}

.cards-container section .title {
    font-weight: 400;
    margin: 1em 0;
}

.cards-container section img {
    width: 100%;
    height: 27em;
    object-fit: cover;
}

:deep(.el-image) {
    width: 100%;
    height: 27em;
    object-fit: cover;
}

.cards-container section p {
    margin: 1em 0;
}


/* 移动端适配 */
@media (max-width: 1248px) {
    .descTitle h2 {
        font-size: 33px;
    }
}
</style>
