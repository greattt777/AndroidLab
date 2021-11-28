## 实验1_Android开发基础——实验报告

### 一、创建一个空的安卓项目

* 打开Android Studio，点击new Project。
* 在左侧边栏选择Phone and Tablet项（默认为该项），再在右边栏选择Empty Activity（默认为该项），然后点击Next。
* 自定义项目名、包名、保存位置、语言和最低支持的SDK版本，然后点击Finish，就完成了空项目的创建。

### 二、将所创建的空的安卓项目用git上传到github上

* 在自己的github上创建一个仓库，并配置ssh密钥。（可以使用ssh来上传，也可以使用http来上传）。

* 在所创的项目的目录文件夹上右击，然后选择Git Bash Here，并配置好用户身份。

* 输入vim .gitignore命令，进行忽略文件的编写。

* 输入git init命令，建立本地仓库；

  输入git add .命令，将该项目的目录文件夹里的所有文件添加到git本地仓库。

  输入git commit -m "commit info"命令，将代码同步到git本地仓库，“ ”里的内容可以自由填写，只是这次提交的一个信息备注。

  输入git remote add origin git@github.com:你的github账号名/你建立的仓库名.git命令，进行远程仓库的添加，origin后面的内容可以在自己的github上所创的仓库上复制，在这里使用的是ssh来添加远程仓库。

  输入git push origin master命令，就将该项目的目录文件夹里的所有文件上传到远程仓库了。
