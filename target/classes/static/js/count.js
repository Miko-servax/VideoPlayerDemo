
let timeStr

let rewriteHis = function(type){
    let origin = window.history[type]
    return function(){
        let rs = origin.apply(this, arguments)
        let e = new Event(type.toLocaleLowerCase())
        e.arguments = arguments
        window.dispatchEvent(e)
        return rs
    }
}

window.history.pushState = rewriteHis('pushState')

window.history.replaceState = rewriteHis('replaceState')

window.addEventListener('onload',(e)=>{
    timeStr = new Date().getTime()
})

window.addEventListener('popstate',()=>{
    let t = new Date().getTime() - timeStr
    timeStr = new Date().getTime()
    console.log('待了时长popstate：'+ t)
})

window.addEventListener('pushstate',()=>{
    let t = new Date().getTime() - timeStr
    timeStr = new Date().getTime()
    console.log('待了时长pushstate：'+ t)
})

window.addEventListener('replacestate',()=>{
    let t = new Date().getTime() - timeStr
    timeStr = new Date().getTime()
    console.log('待了时长replacestate：'+ t)
})

