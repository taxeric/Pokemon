# Pokemon
使用Jetpack的简单测试

# 涉及的技术
- JetPack
  - Room：轻松的数据库访问
  - LiveData：数据更新后同步更新视图
  - DataBinding：绑定数据到界面上
  - Ktx：更简洁的Kotlin代码
- Retrofit2 & OkHttp3：优秀的网络请求库
- [Coil](https://github.com/coil-kt/coil/blob/master/README-zh.md)：基于Kotlin开发的图片加载库
- Material Design：优秀的UI组件库

# 自定义控件
[PkmTypeTextView](https://github.com/taxeric/Pokemon/blob/master/app/src/main/java/com/eric/pokemon/widget/PkmTypeTextView.kt)：用于展示Pokemon属性，根据属性切换不同背景色  
[ProgressTextView](https://github.com/taxeric/Pokemon/blob/master/app/src/main/java/com/eric/pokemon/widget/ProgressTextView.kt)：进度条加载控件，支持自动播放、手动播放  
[测试使用](https://github.com/taxeric/Pokemon/blob/master/app/src/main/java/com/eric/pokemon/test/TestActivity.kt)  
![测试使用](https://github.com/taxeric/Pokemon/blob/master/app/src/demo.gif)

# OpenAPI
[PokeAPI](https://pokeapi.co/) 

This is a full RESTful API linked to an extensive database detailing everything about the Pokémon main game series.

> We've covered everything from Pokémon to Berry Flavors.

# 鸣谢
[PokemonGo](https://github.com/hi-dhl/PokemonGo)

[Pokedex](https://github.com/skydoves/Pokedex)
