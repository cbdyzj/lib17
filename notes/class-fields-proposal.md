# “JavaScript的类字段提案”或“TC39委员出了什么问题？”

译自：[“Class-fields-proposal” or “what went wrong in tc39 committee”](https://medium.com/@igorchulinda/class-fields-proposal-or-what-went-wrong-in-tc39-committee-6ce41efe291)

一直以来，我们都期望有一天能在JavaScript中较为简单地使用其他语言常见的封装语法。比如，我们想要类属性/字段的语法，并且它的实现方式并不会破坏现有的程序。现在看起来，这一天已经到来：在TC39委员会的努力之下，[类字段提案](https://github.com/tc39/proposal-class-fields)已经进入`stage 3`，甚至已经[被Chrome实现](https://www.chromestatus.com/feature/6001727933251584)

老实说，我很乐意写一篇文章，描述为什么您必须使用这个新功能以及如何实现它。但可惜我无法这么做。

### 当前提案说明

参考文档在此不赘述了，具体参考：[原始说明](https://github.com/tc39/proposal-class-fields)，[FAQ](https://github.com/tc39/proposal-class-fields/blob/master/PRIVATE_SYNTAX_FAQ.md)，[规范变更](https://tc39.github.io/proposal-class-fields/)。

### 类字段

类字段说明和用法：

```javascript
class A {
    x = 1;
    method() {
        console.log(this.x);
    }
}
```

从外部代码访问字段：

```javascript
const a = new A();
console.log(a.x);
```

一眼看去稀松平常，有些人可能会说我们在[Babel](https://www.npmjs.com/package/babel-plugin-transform-class-properties)和[TypeScript](http://www.typescriptlang.org/docs/handbook/classes.html)中这样使用多年了。

但有一件事值得注意：这个语法使用`[[Define]]`语义而不是我们习惯的`[[Set]]`语义。这意味着实际上上面的代码**不等价于**以下用法：

```javascript
class A {
    constructor() {
        this.x = 1;
    }
    method() {
        console.log(this.x);
    }
}
```

而**等价于**下述用法：

```javascript
class A {
    constructor() {
        Object.defineProperty(this, "x", {
            configurable: true,
            enumerable: true,
            writable: true,
            value: 1
        });
    }
    method() {
        console.log(this.x);
    }
}
```

尽管在这个例子下，两种用法实际表现几乎没有什么区别，但实际有一个**很重要**的区别。我们假设我们有一个像这样的父类：

```javascript
class A {
    x = 1;

    method() {
        console.log(this.x);
    }
}
```

从该父类派生出一个子类如下：

```javascript
class B extends A {
    x = 2;
}
```

然后使用：

```javascript
const b = new B();
b.method(); // prints 2 to the console
```

然后为了某些（不重要的）原因，我们以一种似乎向后兼容的方式改变了A类：

```javascript
class A {
    _x = 1; // for simplicity let's skip that public interface got new property here
    get x() { return this._x; };
    set x(val) { return this._x = val; };

    method() {
        console.log(this._x);
    }
}
```

对于`[[Set]]`语义，它确实是向后兼容的。 但是对于`[[Define]]`不是。 现在调用`b.method()`会将打印`1`而不是`2`到控制台。原因是在`Object.defineProperty`的作用下，不会调用`A`类声明的属性描述符以及其getter/setter。 因此，在派生类中，我们以类似变量词法作用域的方式隐藏了父类`x`性：

```javascript
const x = 1;
{
    const x = 2;
}
```

我们可以使用[`no-shadowed-variable`](https://palantir.github.io/tslint/rules/no-shadowed-variable/)/[`no-shadow`](https://eslint.org/docs/rules/no-shadow)这样的liner规则帮助我们检测常见的词法作用域变量隐藏。 但是不幸的是，不太可能有人会创建`no-shadowed-class-field`这样的规则帮助我们规避类字段的隐藏。

尽管如此，我并不是`[[Define]]`语义的的坚定反对者（尽管我更喜欢`[[Set]]`语义），因为它有它的好的优点。然而，它的优点并没有超过主要的缺点——我们多年来一直使用`[[Set]]`语义，因为它是`babel6`和`TypeScript`的默认行为。

> 我不得不强调一下，`babel7`改变了默认行为。

您可以在[这里](https://github.com/tc39/proposal-class-fields/issues/151)和[这里](https://github.com/tc39/proposal-class-public-fields/issues/42)了解更多原始讨论。

### 私有字段

我们来看看这个提案中最具争议的部分。 它是如此有争议：

1. 尽管事实上，它已经在[Chrome Canary](https://www.chromestatus.com/feature/6001727933251584)中实现，并且默认情况下公共字段可用，但是私有字段功能仍需额外开启；
2. 尽管事实上，[原始的私有字段提案](https://github.com/tc39/proposal-private-fields)与当前的提案合并，关于分离私有和公有字段的issue一再出现（如：[140](https://github.com/tc39/proposal-class-fields/pull/140#issuecomment-428585587)，[142](https://github.com/tc39/proposal-class-fields/issues/142)，[144](https://github.com/tc39/proposal-class-fields/issues/144)，[148](https://github.com/tc39/proposal-class-fields/issues/148)）；
3. 甚至一些委员会成员（如：[Allen Wirfs-Brock](https://github.com/allenwb)和[Kevin Smith](https://github.com/zenparsing)）也反对它并提供[替代方案](http://tc39.github.io/tc39-notes/2018-09_sept-26.html#revisiting-private-symbols)，但是该提案仍然顺利进入`stage 3`；
4. 该提案的issue数量最多——[当前提案](https://github.com/tc39/proposal-class-fields/issues)的GitHub仓库为131个，[原始提案](https://github.com/tc39/proposal-private-fields/issues)（合并前）的GitHub仓库为96个（相比[BigInt](https://github.com/tc39/proposal-bigint/issues)提案的issue数量为126个），并且大多数issue持[反对观点](https://github.com/tc39/proposal-class-fields/issues/100)；
5. 甚至创建了[单独的issue](https://github.com/tc39/proposal-class-fields/issues/150)，以便统计总结对它的反对意见；
6. 为了证明这一部分的合理性而创建了[单独的FAQ](https://github.com/tc39/proposal-class-fields/blob/master/PRIVATE_SYNTAX_FAQ.md)，然而不够强力论据又导致了新的争论（[133](https://github.com/tc39/proposal-class-fields/issues/133)，[136](https://github.com/tc39/proposal-class-fields/issues/136)）
7. 就我个人而言，几乎花了我所有的空闲时间（有时甚至是工作时间），花了大精力试图对其进行调查，充分了解其背后的局限性和决策，弄明白其形成现状的原因，并提出可行的替代方案；
8. 最后，我决定写这篇评论文章。

声明私有字段的语法：

```javascript
class A {
    #priv;
}
```

并使用以下表示法访问：

```javascript
class A {
    #priv = 1;

    method() {
        console.log(this.#priv);
    }
}
```

这个语法看起来违反直觉，并且很不直观（`this.#priv != this['#priv']`），并且没有使用JavaScript的保留字`privaye`/`protected`（这可能会让已经使用TypeScript的开发者感到伤脑筋），并且为[更多的访问级别](https://github.com/tc39/proposal-class-fields/issues/122)的设计留下隐患。在这样的情境下，我深入的调查并参与了相关讨论。

如果这仅仅与语法形式有关，即主观审美上我们难以接受，那么最后我们或许可以忍受这样的语法并习惯它。 **但是**，还有一个语义问题……

### WeakMap语义

让我们来看看现有提案背后的语义。 我们能够在没有新语法但是保持原有行为的情况下重写前面的示例：

```javascript
const privatesForA = new WeakMap();
class A {
    constructor() {
        privatesForA.set(this, {});
        privatesForA.get(this).priv = 1;
    }

    method() {
        console.log(privatesForA.get(this).priv);
    }
}
```

> 顺便说一句，一名委员会成员使用这种语义创建了一个[小型实用程序库](https://github.com/zenparsing/hidden-state)，这使我们现在就可以使用私有状态。 他的目标是表明这种功能被委员会高估了。其格式化代码只有27行。

很棒，我们可以拥有`硬私有`了，无法从外部代码访问/拦截/跟踪内部的字段，同时我们甚至可以通过以下方式访问同一类的另一个实例的私有：

```javascript
isEquals(obj) {
    return privatesForA.get(this).id === privatesForA.get(obj).id;
}
```

这一切都非常方便，除了这个语义不仅包括`封装`，还包括`brand-checking`（您不必谷歌这个术语，因为您不太可能找到任何相关的信息）。
`brand-checking`与`鸭子类型`相反，从某种意义上说，它根据特定代码确定特定对象而非根据该对象的公共接口确定对象。
实际上，这种检查有其自己的用途——在大多数情况下，它们与在同一进程中安全执行不受信任的代码有关，可以直接共享对象而无需序列化/反序列化开销。

> 但是一些工程师坚持认为这是正确封装的要求。

尽管这是一个非常有趣的可能实现，它涉及`膜`模式（[简短](https://tvcutsem.github.io/js-membranes)和[详尽](https://tvcutsem.github.io/membranes)描述），[`Realms`提案](https://github.com/tc39/proposal-realms)和[Mark Samuel Miller](https://github.com/erights)的计算机科学研究（他也是委员会成员），但是根据我的经验，它似乎并不常见于大多数开发人员的日常工作中。

### `brand-checking`的问题

正如我之前所说，`brand-checking`与鸭子类型相反。 在实践中，这意味着使用以下代码：

```javascript
const brands = new WeakMap();
class A {
    constructor() {
        brands.set(this, {});
    }

    method() {
        return 1;
    }

    brandCheckedMethod() {
        if (!brands.has(this)) throw 'Brand-check failed';

        console.log(this.method());
    }
}
```

`brandCheckedMethod`**只能**被`A`的实例调用，即使target符合此类的所有结构，此方法也会抛出异常：

```javascript
const duckTypedObj = {
    method: A.prototype.method.bind(duckTypedObj),
    brandCheckedMethod: A.prototype.brandCheckedMethod.bind(duckTypedObj),
};
duckTypedObj.method(); // no exception here and method returns 1
duckTypedObj.brandCheckedMethod(); // throws an exception
```

显然，这个例子是刻意设计的，并且这种`鸭子类型`的好处是值得怀疑的。除非我们谈论[`Proxy`](https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/Proxy)。
代理有一个非常重要的使用场景——元编程。 为了使代理执行所有必需的有用工作，代理包装的对象的方法应该在代理的上下文中调用，而不是在目标的中调用：

```javascript
const a = new A();
const proxy = new Proxy(a, {
    get(target, p, receiver) {
        const property = Reflect.get(target, p, receiver);
        doSomethingUseful('get', retval, target, p, receiver);
        return (typeof property === 'function')
            ? property.bind(proxy) // actually bind here is redundant, but I want to focus your attention on method's context
            : property;
    }
});
```

调用proxy.method（）; 将导致做一些在代理中声明并返回`1`的有用工作，当调用`proxy.brandCheckedMethod(); `而不是做一些有用的工作两次将导致抛出异常，因为`a !== proxy`并且`brand-check`失败了。

当然，我们可以在真实目标而不是代理的上下文中执行方法/函数，并且在某些情况下它就足够了（例如实现`膜`模式），但它并非对于所有情况都是够用的（例如，实现反应式属性：[MobX 5](https://github.com/mobxjs/mobx)已经使用代理实现，[Vue.js](https://vuejs.org/)和[Aurelia](https://aurelia.io/)正在试验这种方法以便用于未来版本）。

通常，虽然`brand-check`需要显式声明，但这并不是问题：开发人员只需选择他/她需要哪种权衡以及原因。 在明确的`brand-check`的情况下，它可以以允许其与某些可信代理进行交互的方式实现。

不幸的是，目前的提案没有给予这种灵活性：

```javascript
class A {
    #priv;

    method() {
        this.#priv; // brand-check ALWAYS happens here
    }
}
```

如果在没有用`A`的构造函数构建的对象的上下文中调用`method`方法，该方法将始终抛出异常。这就是最可怕的事实：`brand-check`在这里隐含并与另一个特征——“封装”混合。

虽然几乎所有类型的代码都需要封装，但品牌检查的用例数量非常有限。 当开发人员想要隐藏实现细节时，将它们混合成一种语法将导致意外的`brand-check`，而为了推广这个proposal，宣传`#是新的_`更是雪上加霜。

>您还可以阅读有关[当前提案破坏代理行为的讨论细节](https://github.com/tc39/proposal-class-fields/issues/106)。 在[Aurelia开发者](https://github.com/EisenbergEffect)和[Vue.js作者](https://github.com/yyx990803)参与其中。此外，[我的评论](https://github.com/tc39/proposal-class-fields/issues/158#issuecomment-432809666)描述了代理的几个用例之间的差异，这可能很有趣。 并[讨论了私有字段与`膜`模式之间的关系](https://github.com/tc39/proposal-class-fields/issues/158)。

### 备选方案

除非有其他选择，否则所有这些讨论都没有多大意义。 不幸的是，它们都没有达到第一阶段，因此这些备选提案没有机会得到充分发展。 但是，我想指出其中的一些，以某种方式解决上述问题。

1. [Symbol.private](https://github.com/zenparsing/proposal-private-symbols)——来自其中一名委员会成员的替代提案。
   1. 解决上面描述的所有问题（它可能有自己的问题，但没有进一步开发它很难发现）
   2. 在委员会[最近的会议](http://tc39.github.io/tc39-notes/2018-09_sept-26.html#revisiting-private-symbols)上再次被拒绝，因为缺乏内置的`brand-check`，`膜`模式问题（但[这里](https://github.com/tc39/proposal-class-fields/issues/158#issuecomment-432289884)和[这里](https://github.com/zenparsing/proposal-private-symbols/issues/7#issuecomment-424859518)提供了可行的解决方案）和缺乏方便的语法
   3. 方便的语法可以建立在这个提议之上，如[这里](https://github.com/tc39/proposal-class-fields/issues/149)和[这里](https://github.com/tc39/proposal-class-fields/issues/134)所示
2. [Classes 1.1](https://github.com/zenparsing/js-classes-1.1) - 来自同一作者的较早提议
3. [把`private`作为对象使用](https://github.com/tc39/proposal-class-fields/issues/90)

### 结论

看起来我似乎在责怪委员会——但实际上，我没有。 我只是认为，为了在JS中实现适当的封装，已经经过多年（甚至几十年，取决于选择的起点）的努力，而我们业界更是的发生了很多变化，可能委员会错过了一些变化。导致，相关事体的优先级别可能会变得有些模糊。

不光如此，我们作为一个社区，迫使**TC39**更快地发布功能，但是我们却没有为早期提案提供足够的反馈，结果导致争论很多而能够用来改变某些事情的时间很少。

有[观点](https://github.com/tc39/proposal-class-fields/pull/140#issuecomment-428878848)认为，在这种情况下，该提案过程失败了。

在长期潜水之后，我决定尽我所能，以防止将来发生这种情况。 不幸的是，我做不了多少——只能写写评论文章并在`babel`中实现早期提案。

总的来说，反馈和沟通是最重要的，所以我恳请大家与委员会分享更多的想法。

### 翻译参考

- https://docs.microsoft.com/zh-cn/dotnet/csharp/programming-guide/classes-and-structs/fields
