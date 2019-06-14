# 宏观任务与微观任务

## 宏观任务（Macrotasks）

宿主发起的任务（浏览器、Node.js等）

- setTimeout
- setInternal
- setImmediate
- I/O tasks
- requestAnimationFrame

## 微观任务（Microtasks）

JavaScript引擎发起的任务

- process.nextTick
- Promise
- MutationObserver

事件循环执行宏观任务队列，一个宏观任务可能由一个或多个微观任务构成