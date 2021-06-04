# Web Workers

## 特点

- 同源限制：分配给Worker线程运行的脚本文件，必须与主线程的脚本文件同源。
- DOM限制：Worker线程所在的全局对象，与主线程不一样，无法读取主线程所在网页的DOM对象，也无法使用`document`、`window`、`parent`这些对象。但是，Worker线程可以`navigator`对象和`location`对象。
- 通信联系：Worker线程和主线程不在同一个上下文环境，它们不能直接通信，必须通过消息完成。
- 脚本限制：Worker线程不能执行`alert()`方法和`confirm()`方法，但可以使用XMLHttpRequest对象发出AJAX请求。
- 文件限制：Worker线程无法读取本地文件，即不能打开本机的文件系统（`file://`），它所加载的脚本，必须来自网络。

## API

### 主线程

浏览器原生提供`Worker()`构造函数，用来供主线程生成Worker线程。

```javascript
var myWorker = new Worker(jsUrl, options);
```

`Worker()`构造函数，可以接受两个参数。第一个参数是脚本的网址（必须遵守同源政策），该参数是必需的，且只能加载JS脚本，否则会报错。第二个参数是配置对象，该对象可选。它的一个作用就是指定Worker的名称，用来区分多个Worker线程。

```javascript
// 主线程
var myWorker = new Worker('worker.js', { name : 'myWorker' });

// Worker 线程
self.name // myWorker
```

`Worker()`构造函数返回一个Worker线程对象，用来供主线程操作Worker。Worker线程对象的属性和方法如下。

- Worker.onerror：指定error事件的监听函数。
- Worker.onmessage：指定message事件的监听函数，发送过来的数据在`Event.data`属性中。
- Worker.onmessageerror：指定messageerror事件的监听函数。发送的数据无法序列化成字符串时，会触发这个事件。
- Worker.postMessage()：向Worker线程发送消息。
- Worker.terminate()：立即终止Worker线程。

### Worker线程

Web Worker有自己的全局对象，不是主线程的`window`，而是一个专门为Worker定制的全局对象。因此定义在`window`上面的对象和方法不是全部都可以使用。

Worker线程有一些自己的全局属性和方法。

- self.name：Worker的名字。该属性只读，由构造函数指定。
- self.onmessage：指定`message`事件的监听函数。
- self.onmessageerror：指定messageerror事件的监听函数。发送的数据无法序列化成字符串时，会触发这个事件。
- self.close()：关闭Worker线程。
- self.postMessage()：向产生这个Worker线程发送消息。
- self.importScripts()：加载JS脚本。
