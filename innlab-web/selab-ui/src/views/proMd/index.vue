<template>
    <div class="blog">
        <div class="container">
            <div class="info">
                <div class="bigtitle">
                    <h1>{{ proTitle ? proTitle : 'title' }}</h1>
                    <span v-show="proInfo.itemIntroduction">{{ proInfo.itemIntroduction ? proInfo.itemIntroduction :
                        'description' }}</span>
                    <div class="dinnerInfo">
                        <span v-show="proInfo.author">
                            <svg t="1722909503672" class="icon" viewBox="0 0 1024 1024" version="1.1"
                                xmlns="http://www.w3.org/2000/svg" p-id="4263" width="18" height="18">
                                <path
                                    d="M168.900969 890.578824c11.324235-168.478118 151.612235-302.200471 325.872942-302.200471 43.610353 0 84.088471 2.529882 122.759529 17.648941l40.96-52.284235-6.264471-2.770824a395.143529 395.143529 0 0 0-34.695529-13.372235 256.963765 256.963765 0 0 0 123.904-221.786353 258.590118 258.590118 0 0 0-72.222118-180.705882 240.700235 240.700235 0 0 0-348.762353 0 258.590118 258.590118 0 0 0-72.282353 180.705882 256.963765 256.963765 0 0 0 124.084706 221.786353 398.878118 398.878118 0 0 0-163.478588 103.183059A426.646588 426.646588 0 0 0 90.354146 937.080471C90.354146 951.717647 101.859087 963.764706 116.074616 963.764706h157.816471a35.659294 35.659294 0 0 0 34.755765-36.201412 36.502588 36.502588 0 0 0-34.755765-36.98447H168.900969z m720.414118-407.913412c43.851294 31.322353 56.801882 91.136 31.442824 137.938823l-5.903059 9.938824-205.522824 299.309176a27.105882 27.105882 0 0 1-12.40847 9.878589l-5.180236 1.445647-106.797176 18.371764a26.864941 26.864941 0 0 1-29.214118-16.082823l-1.807059-6.02353-19.516235-108.724706a28.009412 28.009412 0 0 1 1.807059-15.962352l2.710588-4.758589 205.402353-299.309176a103.845647 103.845647 0 0 1 67.222588-43.670588 102.4 102.4 0 0 1 77.70353 17.648941z m-154.624 136.011294l-145.167059 211.546353 12.709647 70.595765 69.330824-12.047059 145.227294-211.546353-82.100706-58.729412v0.180706z m59.030588-84.751059l-5.12 6.445177-22.889411 33.129411 82.04047 58.729412 22.889412-33.249882a51.802353 51.802353 0 0 0-8.673882-69.210353 49.392941 49.392941 0 0 0-68.246589 4.035764v0.120471zM493.930616 129.505882C593.559793 129.505882 674.636499 213.594353 674.636499 316.656941c0 103.303529-81.076706 187.151059-180.705883 187.151059-99.508706 0-180.705882-83.847529-180.705882-187.151059 0-103.183059 81.076706-187.151059 180.705882-187.151059z"
                                    fill="#999999" p-id="4264"></path>
                            </svg>
                            {{ proInfo.author }}
                        </span>
                        <span v-show="proInfo.createTime">
                            <svg style="margin-right: .5%;" t="1721456104580" class="icon" viewBox="0 0 1024 1024"
                                version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="4312" width="18" height="18">
                                <path
                                    d="M716.6464 789.25824h35.83488c20.75136 0 37.71904-12.5696 37.71904-27.97568s-16.96768-27.97568-37.71904-27.97568h-35.83488c-20.75648 0-37.72416 12.5696-37.72416 27.97568 0.31744 15.40608 16.96768 27.97568 37.72416 27.97568zM716.6464 622.336h35.83488c20.75136 0 37.71904-12.5696 37.71904-27.97568 0-15.40096-16.96768-27.97568-37.71904-27.97568h-35.83488c-20.75648 0-37.72416 12.57472-37.72416 27.97568 0.31744 15.40608 16.96768 27.97568 37.72416 27.97568zM494.08 622.336h36.15232c20.74624 0 37.71904-12.5696 37.71904-27.97568 0-15.40096-16.9728-27.97568-37.71904-27.97568H494.08c-20.74624 0-37.71904 12.57472-37.71904 27.97568 0 15.40608 16.9728 27.97568 37.71904 27.97568zM271.51872 622.336h35.83488c20.75136 0 37.72928-12.5696 37.72928-27.97568 0-15.40096-16.97792-27.97568-37.72928-27.97568h-35.83488c-20.74624 0-37.72416 12.57472-37.72416 27.97568 0 15.40608 16.6656 27.97568 37.72416 27.97568zM494.08 789.25824h36.15232c20.74624 0 37.71904-12.5696 37.71904-27.97568s-16.9728-27.97568-37.71904-27.97568H494.08c-20.74624 0-37.71904 12.5696-37.71904 27.97568s16.9728 27.97568 37.71904 27.97568zM271.51872 789.25824h35.83488c20.75136 0 37.72928-12.5696 37.72928-27.97568s-16.97792-27.97568-37.72928-27.97568h-35.83488c-20.74624 0-37.72416 12.5696-37.72416 27.97568s16.6656 27.97568 37.72416 27.97568z"
                                    fill="#8a8a8a" p-id="4313"></path>
                                <path
                                    d="M793.344 176.58368h-56.26368V97.36704v-2.2016a27.38176 27.38176 0 0 0-27.35104-27.35104c-14.7712 0-27.03872 11.95008-27.35104 27.02848V176.896H344.13568V97.36704v-2.2016a27.37664 27.37664 0 0 0-27.34592-27.35104 27.0336 27.0336 0 0 0-27.0336 27.02848V176.896H230.656C140.11392 176.58368 66.56 240.08704 66.56 359.85408v430.6688c0 86.12864 65.38752 157.17376 148.3776 165.03296l578.4064 0.62464c90.53696 0 164.096-74.1888 164.096-165.6576V342.56384c0-91.47904-73.55904-165.98016-164.096-165.98016z m108.45696 630.28224c0 51.87072-44.94848 93.68064-99.96288 93.68064H222.48448c-55.32672 0-99.97312-42.12736-99.97312-93.68064V455.10656h779.60192l-0.31232 351.75936z m0-480.65024V399.4624H122.51136V326.21568c0-45.89568 40.2432-93.67552 111.2832-93.67552h55.9616V316.16c0.31744 14.45376 12.56448 26.40384 27.34592 26.40384a27.3664 27.3664 0 0 0 27.34592-27.35104V232.54016h338.24256V316.16c0.31744 14.45376 12.57472 26.40384 27.35616 26.40384a27.38176 27.38176 0 0 0 27.35104-27.35104V232.54016h53.12c55.0144 0 116.31104 40.54528 111.2832 93.67552z"
                                    fill="#8a8a8a" p-id="4314"></path>
                            </svg>
                            {{ proInfo.createTime }}</span>
                    </div>
                </div>
                <section id="content"><!-- #content -->

                    <v-md-preview :text="mdContent"></v-md-preview>

                </section><!-- end of #content -->

                <section class="action">
                    <div class="mzsm">
                        <p>免责声明</p>
                        <p>参与软件学院创新实验室的科研活动和项目，需自行承担相关风险。实验室对因参与活动导致的任何损失、损害或伤害不负责。所有科研成果和知识产权按实验室规定处理，未经授权不得擅自发布或使用。实验室提供的技术支持和指导仅供参考，对使用建议或指导的后果不承担责任。参与者需遵守设备使用规程和实验室规章制度，违规行为将导致参与资格取消，并视情节追责。尽管实验室尽力保障数据安全，但不对数据泄露或安全事件负责，参与者应自行保护个人信息。实验室保留对活动和项目进行调整或终止的权利，不对因此产生的后果负责。参与即表示已理解并同意以上条款。
                        </p>
                    </div>
                    <ul class="acts">

                        <ul class="shareList">
                            <li class="icon-content" @mouseover="shareTo('qq')">
                                <a href="javascript:;" aria-label="Spotify" data-social="qq">
                                    <div class="filled"></div>
                                    <svg t="1722070846852" class="icon" viewBox="0 0 1024 1024" version="1.1"
                                        xmlns="http://www.w3.org/2000/svg" p-id="2339" width="48" height="48">
                                        <path
                                            d="M512 64C264.6 64 64 264.6 64 512s200.6 448 448 448 448-200.6 448-448S759.4 64 512 64z m210.5 612.4c-11.5 1.4-44.9-52.7-44.9-52.7 0 31.3-16.2 72.2-51.1 101.8 16.9 5.2 54.9 19.2 45.9 34.4-7.3 12.3-125.6 7.9-159.8 4-34.2 3.8-152.5 8.3-159.8-4-9.1-15.2 28.9-29.2 45.8-34.4-35-29.5-51.1-70.4-51.1-101.8 0 0-33.4 54.1-44.9 52.7-5.4-0.7-12.4-29.6 9.4-99.7 10.3-33 22-60.5 40.2-105.8-3.1-116.9 45.3-215 160.4-215 113.9 0 163.3 96.1 160.4 215 18.1 45.2 29.9 72.8 40.2 105.8 21.7 70.1 14.6 99.1 9.3 99.7z"
                                            p-id="2340" fill="currentColor"></path>
                                    </svg>
                                </a>
                                <div class="tooltip wxtooltip">
                                    <span>手机QQ扫描二维码</span>
                                    <span>点击右上角 ··· 按钮分享到QQ</span>
                                    <img class="qrImg" :src="qrcodeUrlqq" alt="二维码" v-if="qrcodeUrlqq" />

                                </div>
                            </li>
                            <li class="icon-content" @click="shareTo('qqkj')">
                                <a href="javascript:;" aria-label="Pinterest" data-social="qqkj">
                                    <div class="filled"></div>
                                    <svg t="1722070924439" class="icon" viewBox="0 0 1024 1024" version="1.1"
                                        xmlns="http://www.w3.org/2000/svg" p-id="2364" width="48" height="48">
                                        <path
                                            d="M482.7 600.3h0.9c3.6-4.4 9.2-6.4 14-9.6 10.7-7.1 21.4-14.5 32.4-21.9 28.7-19.2 56.8-40.6 84.9-60.4 7.6-5.4 24.9-15 29.8-21.9 2.5-3.6 4.4-10.2 1.7-14-2.2-6.5-11.8-4.4-20.1-4.4h-57.8c-5.6 3.4-36.4 0.9-45.5 0.9l-112.9 1.8c-9.2 0-40.7-2.6-46.4 0.9 11.5-0.1 23.3 2.1 33.2 4.4 13.3 3.1 27 2.4 39.4 5.2l14 0.9c20.7 4.8 44.8 4.8 65.6 9.6 13.3 3.1 27 2.4 39.4 5.3 6 1.4 12.6-0.5 16.6 2.6h-1.7c-11.5 10.3-33.3 17.7-47.3 26.3-34.8 21.3-71.4 40.3-105.9 62.1-7.4 4.7-20.5 12.1-24.5 20.1-2.7 5.4-1.2 17.8 3.5 20.1h14l244.1-11.4v-2.6l-171.5-13.1v-0.9z m28.4-535.1c-247.4 0-448 200.6-448 448s200.6 448 448 448 448-200.6 448-448-200.5-448-448-448z m247.2 415.2c-7.5 5.7-12.8 13.7-20.1 19.2-20.3 15.4-38 35.5-56 53.4-6.1 6.1-32.3 26.5-33.2 34.1 2.8 4.3 4.5 21.1 5.2 27.1 7.8-0.2 22.1-0.9 27.1 2.6l-26.3 0.9L681.3 770h-1.7c-8.1-7.9-30.8-16-42-21.9-28.7-14.9-57.6-29.6-86.6-44.6-10-5.2-20.1-10.3-30.6-15.7-2.2-1.1-7.2-6.1-10.5-5.3-1.3 0.4-4.6 3.6-6.1 4.4-9.3 4.8-18.4 9.4-27.1 14-31.3 16.3-62.3 31-93.6 47.2-12.6 6.6-28.2 17.8-42 21.9l32.4-186.4c-9.4-6.2-16.6-15.9-24.5-23.6-14.2-13.9-28.8-29.2-44.6-41.1L286 499.6c-12.5-9.4-23.1-21.4-34.1-32.4-5-5-11.8-8.8-15.8-14.9 10.8-1 23.1-2.9 33.3-5.3 14.9-3.5 29.9-2.9 43.7-6.1 20.4-4.8 41.7-4.1 61.3-8.8 11.5-2.7 44.9-2.7 51.6-7.9 4.2-4.7 5.7-11.7 8.8-17.5 6.3-12.2 12.6-24.9 19.2-37.6 19.3-37.1 38.7-75 56.9-112.9 0.7 0.9 0.3 0.2 0.9 1.7 3.7 3.2 4.6 8.6 7 13.1 6.2 12 12 24.4 18.4 36.8L596.7 425c24.2 2.3 52.9 5.9 76.1 11.4 20.4 4.8 41.7 4.1 61.2 8.8 11.7 2.8 45.6 2.5 52.5 7.9-11.1 7.3-18 19.6-28.2 27.3z"
                                            p-id="2365" fill="currentColor"></path>
                                    </svg>
                                </a>
                                <div class="tooltip">转发到QQ空间</div>
                            </li>
                            <li class="icon-content" @click="shareTo('wb')">
                                <a href="javascript:;" aria-label="Dribbble" data-social="wb">
                                    <div class="filled"></div>
                                    <svg t="1722070614574" class="icon" viewBox="0 0 1025 1024" version="1.1"
                                        xmlns="http://www.w3.org/2000/svg" p-id="4305" width="48" height="48">
                                        <path
                                            d="M511.9535 63.941187c-247.458725 0-448.062313 200.602588-448.062313 448.061313 0 247.453725 200.603588 448.057313 448.062313 448.057313 247.453725 0 448.052313-200.603588 448.052313-448.057313C960.005813 264.543775 759.407225 63.941187 511.9535 63.941187L511.9535 63.941187zM470.406378 735.326154c-114.514335 0-231.571678-55.497163-231.571678-146.77843 0-47.72214 30.232089-102.906301 82.306241-154.983454 69.523204-69.506204 150.599441-101.167296 181.093531-70.660207 13.454039 13.437039 14.757043 36.716108 6.107018 64.506189-4.504013 13.997041 13.138038 6.244018 13.138038 6.278018 56.197165-23.528069 105.222308-24.916073 123.142361 0.688002 9.562028 13.64204 8.647025 32.776096-0.166 54.950161-4.078012 10.21503 1.256004 11.796035 9.026026 14.129041 31.665093 9.816029 66.907196 33.559098 66.907196 75.400221C720.390111 648.104899 620.530818 735.326154 470.406378 735.326154L470.406378 735.326154zM677.718986 445.086304c3.706011-11.441034 1.385004-24.485072-7.248021-34.0541-8.624025-9.552028-21.373063-13.176039-33.131097-10.693031L637.339867 400.317173c-9.809029 2.137006-19.472057-4.159012-21.575063-13.959041-2.111006-9.838029 4.154012-19.515057 13.985041-21.609063 24.05307-5.111015 50.106147 2.321007 67.748198 21.895064 17.682052 19.575057 22.413066 46.235135 14.853044 69.626204-3.086009 9.573028-13.344039 14.787043-22.904067 11.728034-9.560028-3.095009-14.788043-13.365039-11.706034-22.917067l-0.018 0L677.718986 445.086304zM783.742296 479.324404c-0.004 0.018-0.004 0.06-0.004 0.081-3.599011 11.095033-15.523045 17.16905-26.610078 13.57904-11.133033-3.595011-17.21505-15.489045-13.62504-26.610078l-0.004-0.009c11.027032-34.1401 4.035012-73.082214-21.715064-101.628298-25.772076-28.554084-63.770187-39.461116-98.88529-32.003094-11.420033 2.431007-22.651066-4.856014-25.088074-16.267048-2.444007-11.397033 4.838014-22.638066 16.258048-25.083073l0.021 0c49.359145-10.492031 102.829301 4.829014 139.085407 45.025132C789.439313 376.562103 799.213341 431.303264 783.742296 479.324404L783.742296 479.324404zM783.742296 479.324404"
                                            fill="currentColor" p-id="4306"></path>
                                        <path
                                            d="M624.54883 570.13167c-5.924017-59.970176-84.807248-101.273297-176.195516-92.23827-91.371268 9.034026-160.659471 64.97919-154.723453 124.959366 5.936017 60.001176 84.814248 101.304297 176.195516 92.28627C561.213644 686.09901 630.476847 630.149846 624.54883 570.13167L624.54883 570.13167zM528.526548 619.374815c-18.647055 42.179124-72.288212 64.669189-117.792345 49.991146-43.932129-14.176042-62.532183-57.556169-43.298127-96.632283 18.890055-38.315112 68.035199-59.984176 111.517327-48.671143C523.957535 535.692569 546.920602 578.144694 528.526548 619.374815L528.526548 619.374815zM528.526548 619.374815"
                                            fill="currentColor" p-id="4307"></path>
                                        <path
                                            d="M435.544276 587.23972c-14.147041-5.932017-32.439095 0.166-41.170121 13.860041-8.838026 13.74904-4.693014 30.126088 9.359027 36.525107 14.254042 6.509019 33.179097 0.329001 42.022123-13.77104 8.680025-14.241042 4.107012-30.511089-10.20603-36.614107L435.544276 587.23972zM435.544276 587.23972"
                                            fill="currentColor" p-id="4308"></path>
                                        <path
                                            d="M470.419378 572.780678c-5.428016-2.154006-12.219036 0.457001-15.404045 5.796017-3.090009 5.368016-1.384004 11.484034 4.057012 13.71904 5.534016 2.278007 12.599037-0.350001 15.796046-5.812017C477.9194 580.982702 475.937394 574.798684 470.419378 572.780678L470.419378 572.780678zM470.419378 572.780678"
                                            fill="currentColor" p-id="4309"></path>
                                    </svg>
                                </a>
                                <div class="tooltip">转发到微博</div>
                            </li>
                            <li class="icon-content" @mouseover="shareTo('wx')">
                                <a href="javascript:;" aria-label="Telegram" data-social="wx">
                                    <div class="filled"></div>
                                    <svg t="1722070899447" class="icon" viewBox="0 0 1024 1024" version="1.1"
                                        xmlns="http://www.w3.org/2000/svg" p-id="2359" width="48" height="48">
                                        <path
                                            d="M571.648 511.94c-10.257 0-20.517 9.525-20.517 21.25 0 9.527 10.255 19.053 20.517 19.053 15.393 0 26.385-9.527 26.385-19.053 0-11.724-10.99-21.25-26.385-21.25v0zM504.225 413c16.123 0 26.385-10.257 26.385-25.651 0-16.123-10.261-25.649-26.385-25.649-15.39 0-30.048 9.527-30.048 25.649-0.001 15.396 14.659 25.651 30.048 25.651v0zM512.287 62.492c-248.427 0-449.813 201.386-449.813 449.813 0 248.425 201.386 449.812 449.813 449.812 248.425 0 449.812-201.386 449.812-449.812 0.001-248.427-201.385-449.813-449.812-449.813v0zM427.275 626.265c-27.119 0-46.906-4.394-72.556-11.725l-74.019 37.38 21.257-63.031c-52.035-36.64-82.818-82.811-82.818-139.243 0-99.67 93.808-175.889 208.137-175.889 101.135 0 191.279 60.097 208.867 145.113-7.328-1.469-13.926-2.199-19.788-2.199-99.67 0-176.619 74.75-176.619 164.894 0 15.388 2.199 29.313 5.863 43.972-5.863 0.728-12.461 0.728-18.324 0.728v0zM732.882 698.085l14.655 52.77-55.697-31.512c-21.252 4.394-41.773 10.99-63.025 10.99-98.206 0-175.889-67.422-175.889-150.968 0-83.547 77.685-150.97 175.889-150.97 93.072 0 176.619 67.423 176.619 150.97 0 46.9-31.512 88.673-72.552 118.721v0zM359.117 361.7c-15.39 0-31.512 9.527-31.512 25.649 0 15.395 16.122 25.656 31.512 25.656 14.66 0 26.385-10.261 26.385-25.656 0-16.122-11.725-25.649-26.385-25.649v0zM686.712 511.94c-10.995 0-20.522 9.525-20.522 21.25 0 9.527 9.527 19.053 20.522 19.053 14.655 0 25.649-9.527 25.649-19.053 0-11.724-10.994-21.25-25.649-21.25v0zM686.712 511.94z"
                                            p-id="2360" fill="currentColor"></path>
                                    </svg>
                                </a>
                                <div class="tooltip wxtooltip">
                                    <span>手机微信扫描二维码</span>
                                    <span>点击右上角 ··· 按钮分享到微信</span>
                                    <img class="qrImg" :src="qrcodeUrl" alt="二维码" v-if="qrcodeUrl" />

                                </div>

                            </li>

                            <li class="icon-content" @click="copyLink()">
                                <a href="javascript:;" aria-label="Dribbble" data-social="link">
                                    <div class="filled"></div>
                                    <svg t="1722163869467" class="icon" viewBox="0 0 1024 1024" version="1.1"
                                        xmlns="http://www.w3.org/2000/svg" p-id="5150" width="48" height="48">
                                        <path
                                            d="M512 1024C229.228 1024 0 794.772 0 512S229.228 0 512 0s512 229.228 512 512-229.228 512-512 512z m54.545-565.02l-1.798-1.764a102.207 102.207 0 0 0-11.002-9.523l-35.453 35.442c4.176 2.446 8.078 5.393 11.605 8.92l1.866 1.763a58.277 58.277 0 0 1 0 82.341l-96.904 96.882a58.334 58.334 0 0 1-82.341 0l-1.832-1.798a58.243 58.243 0 0 1 0-82.306l43.816-43.828a149.675 149.675 0 0 1-10.866-58.732l-67.812 67.72c-41.836 41.825-41.836 110.251 0 152.053l1.787 1.798c41.836 41.79 110.228 41.79 152.052 0l96.882-96.916c41.757-41.825 41.757-110.25 0-152.052z m141.38-141.37l-1.82-1.797c-41.802-41.825-110.228-41.825-152.053 0l-96.882 96.916c-41.824 41.79-41.824 110.216 0 152.053l1.798 1.763c3.505 3.425 7.225 6.576 11.002 9.523l35.454-35.476a57.845 57.845 0 0 1-11.583-8.92l-1.798-1.763a58.311 58.311 0 0 1 0-82.375l96.905-96.882a58.197 58.197 0 0 1 82.284 0l1.798 1.797a58.277 58.277 0 0 1 0 82.341l-43.76 43.828c7.612 18.796 11.196 38.81 10.844 58.766l67.789-67.755c41.824-41.79 41.824-110.216 0.022-152.007z"
                                            p-id="5151" fill="currentColor"></path>
                                    </svg>
                                </a>
                                <div class="tooltip">点击复制本文链接</div>
                            </li>
                        </ul>



                    </ul>


                </section>
            </div>
            <div class="about">
                <aside id="sidebar"><!-- sidebar -->
                    <h3>部 门 项 目</h3>
                    <ul>
                        <li v-for="(item, index) in ProList">
                            <div class="titleNum">{{ index + 1 }}</div>
                            <span @click="linkTo(item.itemId)"> {{ item.itemTitle }}</span>
                        </li>

                    </ul>

                </aside><!-- end of sidebar -->
            </div>
        </div>
        <el-backtop class="backtop" :right="50" :bottom="60" />
        <el-backtop class="mobilebacktop" :right="10" :bottom="10" />

    </div>
</template>
<script setup lang="ts">
import { ref, reactive, onMounted, watch } from "vue"
import { useRouter } from 'vue-router';
import { useRoute } from 'vue-router';
import { getProInfo, getMd } from '@/api/pro/pro'
import { getPros } from '@/api/pro/pro'
import { getActs } from '@/api/activity/activity'
import bus from '@/eventBus';
import router from '@/router';
import { parseLanzouLink } from '@/utils/getFileByBackend';
import { toQQzone, toQQ, toWeibo, toWechat } from './share.js'
import QRCode from 'qrcode';
import useClipboard from 'vue-clipboard3'
import { ElMessage } from 'element-plus'

const route = useRouter();
const routeQuery = useRoute();

const proPart = ref('')
const mdContent = ref()
const proTitle = ref('')

// 根据路由参数获取对应活动id
const actId = ref(route.currentRoute.value.query.actid);
const proId = ref(route.currentRoute.value.query.id);
const partName = ref(route.currentRoute.value.query.part);

interface proInfoType {
    itemId: number;
    itemTitle: string;
    itemIntroduction: string;
    headerImage: number;
    contentMarkdown: string;
    isTop: boolean;
    createTime: string;
    updateTime: string;

}
const proInfo = ref<proInfoType | null>({});
// 根据id获取项目详情
const getPro = async () => {
    try {
        const result = await getProInfo(proId.value);

        proInfo.value = result.data;
        proTitle.value = result.data.itemTitle;
        // proPart.value = result.data.itemIntroduction;

        const base64Content = result.data.markdownBase64;
        const byteArray = Uint8Array.from(atob(base64Content), c => c.charCodeAt(0));
        const decoder = new TextDecoder('utf-8');
        const decodedContent = decoder.decode(byteArray);
        console.log('Decoded Content:', decodedContent);
        mdContent.value = decodedContent;



    } catch (error) {
        console.error('Error fetching data:');
    } finally {

    }
};


const params = ref({
    pageNum: 1,
    pageSize: 10,
    department: partName.value
})
const ProList = ref<Array<{ itemId: number; itemTitle: string; itemIntroduction: string; headerImage: object; createTime: string }>>([]);
const getProList = async () => {
    try {
        const result = await getPros(params.value);
        ProList.value = result.data.records;

        ProList.value.forEach(async e => {

            e.headerImage = await parseLanzouLink(e.headerImage);
            console.log('e.headerImage: ', e.headerImage);

        })

        total.value = result.data.total;

    } catch (error) {
        console.error('Error fetching data:');
    } finally {

    }
};


// 点击跳转
const linkTo = (item: number) => {
    router.push({ path: '/proMd', query: { id: item, name: partName.value } });

}

// Base64 decode utility
function decodeBase64(base64Str: string): string {

    try {
        const byteArray = Uint8Array.from(atob(base64Str), c => c.charCodeAt(0));
        const decoder = new TextDecoder('utf-8');
        const decodedContent = decoder.decode(byteArray);
        console.log('decodedContent', decodedContent);

        return decodedContent;
    } catch (error) {
        console.log('decodeerror: ', error);
        return base64Str;
    }

}

const videoLoaded = ref(false);

const handleVideoLoad = () => {
    videoLoaded.value = !videoLoaded.value;
};

const getImgLink = async (img) => {
    const result = await parseLanzouLink(img);
    return result;
}
const getLink = async (img) => {
    const result = await getImgLink(img);
    return result;
}


const shareTo = (type) => {
    const url = window.location.href;
    const title = proInfo.value.itemTitle;

    switch (type) {
        case 'qq':
            getQQCode();
            break;
        case 'qqkj':
            toQQzone(url, title);
            break;
        case 'wb':
            toWeibo(url, title);
            break;
        case 'wx':
            // toWechat(url, title);
            getWxCode();
            break;

        default:
            break;
    }
}

const qrcodeUrl = ref('');
const qrcodeUrlqq = ref('');
const getQQCode = () => {
    const url = window.location.href;
    if (url) {
        QRCode.toDataURL(url, { width: 500, height: 500 }, (err, dataUrl) => {
            if (err) {
                console.error(err);
            } else {
                qrcodeUrlqq.value = dataUrl;
            }
        });
    }
}
const getWxCode = () => {
    const url = window.location.href;
    if (url) {
        QRCode.toDataURL(url, { width: 500, height: 500 }, (err, dataUrl) => {
            if (err) {
                console.error(err);
            } else {
                qrcodeUrl.value = dataUrl;
            }
        });
    }
}

// 点击复制链接
const { toClipboard } = useClipboard()
const copyLink = async () => {
    const link = window.location.href;
    try {
        await toClipboard(link)
        ElMessage({
            message: '链接已成功复制',
            type: 'success',
            plain: true,
        });
    } catch (e) {
        console.error(e)
    }
}

// 监视路由参数的变化
watch(
    () => routeQuery.query,
    () => {
        // 执行页面刷新操作
        router.go(0);
        handleVideoLoad();
    },
    { deep: true } // 深度监视对象的变化
);

onMounted(async () => {
    setTimeout(() => {
        bus.emit('loading', false);
    }, 200);

    window.scrollTo({ top: 0, behavior: 'smooth' });

    getPro();
    getProList();

});

</script>
<style scoped>
.blog {
    display: flex;
    padding: 14em 12em;
    background-color: #f5f6f7;
    background-image: linear-gradient(180deg, #f6f1ef 0, #f0f1f6 42%, #ebf3fb 100%);
}

.container {
    width: 100%;
    background-color: white;
    display: flex;
}

.info {
    display: flex;
    flex-direction: column;
    width: 78%;
    /* height: 1200px; */
    padding: 4%;
    padding-top: 3%;
    border-right: 5px solid rgb(240, 240, 240);

}

.bigtitle {
    display: flex;
    flex-direction: column;
    padding-bottom: 2%;
    margin-bottom: 2%;
    border-bottom: 1px solid rgb(198, 197, 197);


}

.bigtitle span {
    display: flex;
    align-items: center;
    color: gray;
    margin-top: 1%;
}

.dinnerInfo {
    display: flex;
}

.dinnerInfo span:nth-child(1) {
    width: 5em;
}

.dinnerInfo span:nth-child(2) {
    width: 18em;
}

/* content */
#content {
    display: flex;
    flex-direction: column;
}



.pre {
    white-space: pre-wrap;
    /* 保留空格和换行，同时允许换行 */
    word-wrap: break-word;
    /* 长单词换行 */
    overflow-wrap: break-word;
    /* 保证文本在容器宽度内换行 */
    margin: 0;
    /* 移除默认的边距 */
    padding: 0;
    /* 移除默认的内边距 */
}

/* md */
:deep(.github-markdown-body) {
    padding: 0 !important;
    margin: 0 !important;
}

#content p,
ul,
ol,
hr {
    margin-bottom: 24px;
}

#content ul ul,
ol ol,
ul ol,
ol ul {
    margin-bottom: 0;
}

#content h1,
h2,
h3,
h4,
h5,
h6 {
    color: #333;
    margin: 0 0 20px 0;
    line-height: 1.5em;
}

article img {
    width: 98%;
    margin-right: 5%;
    margin-bottom: 5%;
    border: none;
    -webkit-box-shadow: 3px 3px 7px #777;
    -moz-box-shadow: 3px 3px 7px #777;
}

:deep(.el-image) {
    width: 98%;
    margin-right: 5%;
    margin-bottom: 5%;
    border: none;
    -webkit-box-shadow: 3px 3px 7px #777;
    -moz-box-shadow: 3px 3px 7px #777;
}

:deep(.el-image-viewer__close) {
    top: 5%;
    right: 40px;
}

article video {
    width: 100%;
    margin-right: 5%;
    margin-bottom: 5%;
    border: none;
    -webkit-box-shadow: 3px 3px 7px #777;
    -moz-box-shadow: 3px 3px 7px #777;
}

/* sidebar and footer widget blocks */

aside h3 {
    border-left: 3px solid #134c86;
    padding-left: 4%;
    font-size: 18px;
}

aside ul {
    list-style: none;
    margin-bottom: 20px;
    /* margin-top: -15px; */
}

aside ul li {
    display: flex;
    margin: 8% 0;
}

aside ul li .titleNum {
    width: 8%;
    height: 8%;
    background: #ebebeb;
    display: flex;
    justify-content: center;
    align-items: center;
    border-radius: 21px;
}

aside ul li span {
    margin-left: 3%;
}

aside ul li span:hover {
    cursor: pointer;
    color: gray;
}

aside .imgs {
    width: 100%;
    height: 100%;
    display: flex;
    flex-direction: column;

}

aside .imgs .imgbox {
    width: 100%;
    height: 11em;
    overflow: hidden;
    margin-bottom: 6%;

}

aside .imgs .imgbox img {
    cursor: pointer;
    transition: all .2s;
    width: 100%;
    height: 100%;
    object-fit: cover;
    /* 保持宽高比例，裁剪多余部分 */
}

aside .imgs .imgbox img:hover {
    transform: scale(1.1);
}

.mobilebacktop {
    display: none;
}

/* about */
.about {
    width: 28%;
    padding: 30px;
    padding-top: 60px;
}

.action {
    margin-top: 200px;
    width: 100%;
}

.mzsm {
    position: relative;
    margin: 25px 0 15px;
    padding: 45px 45px 30px;
    border-color: #c0c6cc;
    background-color: #f8f8fa;
    font-family: Lantinghei SC, Open Sans, Arial, Hiragino Sans GB, Microsoft YaHei, \\5fae\8F6F\96c5\9ED1, STHeiti, WenQuanYi Micro Hei, SimSun, sans-serif;
}

.mzsm p {
    display: block;
    line-height: 30px;
    margin-bottom: 15px;
}

.mzsm:after,
.mzsm:before {
    position: absolute;
    display: block;
    width: 1pc;
    height: 14px;
    content: '';
}

.mzsm:before {
    top: 20px;
    left: 20px;
    background: url(public/img/disImg/blockquote.png) no-repeat 0 0;
}

.mzsm:after {
    right: 20px;
    bottom: 20px;
    background: url(public/img/disImg/blockquote.png) no-repeat -1pc 0;
}

.acts {
    display: flex;
    margin: 6% 0;

}

.acts li {
    margin-right: 1%;
    display: flex;
    align-items: center;
    color: #bfbfbf;
    cursor: pointer;

}

.shareList {
    list-style: none;
}

.icon-content::after {
    content: "";
    display: block;
    height: 0px;
    transition: height 0.3s ease-in-out;
    pointer-events: none;
}

.icon-content:hover::after {
    height: 10px;
}

.shareList {
    display: flex;
    justify-content: center;
    align-items: center;
}

.shareList .icon-content {
    cursor: pointer;
    margin: 0 10px;
    position: relative;
}

.shareList .icon-content .tooltip {
    white-space: nowrap;
    /* 不允许内容换行 */
    position: absolute;
    bottom: -30px;
    left: 50%;
    transform: translateX(-50%);
    color: #fff;
    padding: 6px 10px;
    border-radius: 5px;
    opacity: 0;
    pointer-events: none;
    visibility: hidden;
    font-size: 14px;
    transition: all 0.3s ease;
}

.wxtooltip {
    height: 16em;
    top: 110%;
    display: flex;
    flex-direction: column;
    justify-content: space-evenly;
    align-items: center;
}

.shareList .icon-content:hover .tooltip {
    opacity: 1;
    visibility: visible;
    bottom: -40px;
}

.shareList .icon-content:hover .wxtooltip {
    opacity: 1;
    visibility: visible;
    top: 110%;
    left: 7em;
}

.shareList .icon-content a {
    position: relative;
    overflow: hidden;
    display: flex;
    justify-content: center;
    align-items: center;
    width: 50px;
    height: 50px;
    border-radius: 50%;
    color: #cdcdcd;
    background-color: #fff;
    transition: all 0.3s ease-in-out;
}

.shareList .icon-content a:hover {
    box-shadow: 3px 2px 45px 0px rgb(0 0 0 / 12%);
}

.shareList .icon-content a svg {
    position: relative;
    z-index: 1;
    width: 100%;
    height: 100%;
}

.shareList .icon-content a:hover {
    color: white;
}

.shareList .icon-content a .filled {
    position: absolute;
    top: auto;
    bottom: 0;
    left: 0;
    width: 100%;
    height: 0;
    background-color: #000;
    transition: all 0.3s ease-in-out;
}

.shareList .icon-content a:hover .filled {
    height: 100%;
}

.shareList .icon-content a[data-social="qq"] .filled,
.shareList .icon-content a[data-social="qq"]~.tooltip {
    background-color: #53d9ff;
}

.shareList .icon-content a[data-social="qqkj"] .filled,
.shareList .icon-content a[data-social="qqkj"]~.tooltip {
    background-color: #ffce02;
}

.shareList .icon-content a[data-social="wb"] .filled,
.shareList .icon-content a[data-social="wb"]~.tooltip {
    background-color: #e22428;
}

.shareList .icon-content a[data-social="wx"] .filled,
.shareList .icon-content a[data-social="wx"]~.tooltip {
    background-color: #1aad19;
}

.shareList .icon-content a[data-social="link"] .filled,
.shareList .icon-content a[data-social="link"]~.tooltip {
    background-color: #02406f;
}

.qrImg {
    width: 10em !important;
    height: auto !important;
}


.liked {
    color: #4d7190 !important;
}

/* comment */
.comment {
    width: 100%;
    display: flex;
    align-items: center;
    justify-content: space-between;
}

/* 移动端适配 */
@media (max-width: 1284px) {

    .blog {
        width: 100%;
        display: flex;
        padding: 6em 2em;

    }

    .bigtitle h1 {
        font-size: 1.5em;
    }

    .info {
        display: flex;
        flex-direction: column;
        width: 100%;
        /* height: 1200px; */
        padding: 4%;
        padding-top: 3%;
        border-right: none;
    }

    .about {
        width: 28%;
        padding: .4em;
        padding-top: 1.5em;
        display: none;
    }

    .mobilebacktop {
        display: flex;
    }

    .backtop {
        display: none;
    }


    .mzsm {
        position: relative;
        margin: 25px 0 15px;
        padding: 3em 1em;
    }

    .mzsm p {
        display: block;
        line-height: 1.3em;
        margin-bottom: .5em;
        font-size: 0.6em;
    }

    .acts {

        width: 100%;
    }

    .shareList {

        width: 100%;
        justify-content: flex-start;
    }

    .shareList .icon-content {

        margin: 0 .3em;

    }

    .shareList .icon-content a {
        position: relative;
        overflow: hidden;
        display: flex;
        justify-content: center;
        align-items: center;
        width: 30px;
        height: 50px;
        border-radius: 50%;
        color: #cdcdcd;
        background-color: #fff;
        transition: all 0.3s ease-in-out;
    }

    .shareList .icon-content .tooltip {

        z-index: 200;
    }

    .about {
        width: 28%;
        padding: .4em;
        padding-top: 1.5em;
    }

    aside h3 {
        padding-left: 4%;
        font-size: 0.6em;
    }


}
</style>
