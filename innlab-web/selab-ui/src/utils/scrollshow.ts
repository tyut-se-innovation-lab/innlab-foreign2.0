// scrollShow.ts
const scrollShow = (): void => {
    const elements = document.querySelectorAll<HTMLElement>('.acShow');
    console.log("acshowele", elements);

    const clientHeight = window.innerHeight || document.documentElement.clientHeight || document.body.clientHeight;
    const scrollTop = document.documentElement.scrollTop || document.body.scrollTop;

    elements.forEach((element: HTMLElement) => {
        const elementTop = element.getBoundingClientRect().top + scrollTop;
        const height = element.clientHeight;
        console.log('jinru', elementTop + height / 2 - scrollTop, elementTop);
        if (elementTop + height / 2 - scrollTop <= elementTop) {
            console.log('jinru');

            element.style.opacity = "1";

        } else {
            element.style.opacity = "0"; // Optionally reset opacity when element is not in view
        }
    });
};

export default scrollShow;
