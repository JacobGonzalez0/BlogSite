(()=>{
    stroll.bind(".scroller")

    //fixes window resize bug with stroll lib
    window.addEventListener('resize', ()=>{
        stroll.unbind(".scroller")
        stroll.bind(".scroller")
    });
    
})();
