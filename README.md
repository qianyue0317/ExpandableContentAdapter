# ExpandableContentAdapter
点击标题而展开内容的adapter,ListView每个条目设置一个标题布局和一个内容布局,当点击每个条目的标题的时候,
内容会展开或者收起.使用时只需继承ExpandDetailAdapter,并添加相应的构造方法及实现抽象方法即可.在bindData()方法中进行数据的绑定.
构造方法参数说明,mContext:上下文,data:数据源,titleViewId:标题布局id,contentViewId:内容布局Id.方法setDuration()设置展开或收起动画的
持续时长,默认是200毫秒.
## 使用
  直接将项目中的相应类复制到需要的地方即可;或者下载libs文件夹中的jar包,添加.

![expand](https://github.com/qianyue0317/ExpandableContentAdapter/blob/master/expandable.gif) 
