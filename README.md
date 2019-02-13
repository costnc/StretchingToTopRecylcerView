# StretchingToTopRecylcerView
RecyclerView 의 첫번째 뷰를 Stretching을 하기 위한 RecyclerView

![](st1.gif)

* See the sample code -> app/src

How to
To get a Git project into your build:

Step 1. Add the JitPack repository to your build file

gradle
maven
sbt
leiningen
Add it in your root build.gradle at the end of repositories:

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
Step 2. Add the dependency

	dependencies {
	        api 'com.github.costnc:StretchingToTopRecylcerView:0.1'
		api 'com.android.support:recyclerview-v7:28.0.0'
	}
