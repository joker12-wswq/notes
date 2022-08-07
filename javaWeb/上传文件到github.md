--------------->7.26

#### 前提

1.安装 git    [Git - 下载 (git-scm.com)](https://git-scm.com/downloads)

2.在[GitHub](https://github.com/)上，创建了仓库   

#### 步骤

1.创建一个空文件

2.右击文件夹，选择Git Bash Here

3.输入控制命令

①将这个目录变成git可以管理的仓库

~~~
git init
~~~

②添加文件，‘ .'表示添加文件夹下全部文件，可以将’  . '改成文件名

~~~
git add .
~~~

③将暂存区文件提交到本地仓库

~~~
git commit -m '注释说明'
~~~

④将本地仓库关联到github上

~~~
git remote add origin https:......git
~~~

~~~
git remote add origin https://github.com/joker12-wswq/notes.git
~~~

⑤获取远程库与本地库合并  master 为上面 4 处的分支名

~~~
git pull --rebase origin master
~~~

###### ==git使用报错:fatal: Couldn't find remote ref master的解决方法==

如果是新建的仓库（ repositories ）的话在pull代码的时候，出现这个提示，可以忽略不计，直接提交就可以。

==报了这个错：fatal: unable to access ‘https://github.com/…’: OpenSSL SSL_read: Connection was reset, errno 10054==

产生原因：一般是这是因为服务器的[SSL](https://so.csdn.net/so/search?q=SSL&spm=1001.2101.3001.7020)证书没有经过第三方机构的签署，所以才报错

参考网上解决办法：解除ssl验证后，再上传就OK了

~~~
`git config --global http.sslVerify "false"`
~~~

⑥将当前文件推送到远程master 分支

~~~
git push -u origin master
~~~

###### ==git命令 fatal: The remote end hung up unexpectedly 出错 解决方法==

原因是推送的文件太大。

修改提交缓存大小为500M，或者更大的数字

~~~
git config --global http.postBuffer 524288000

git config --global http.postBuffer 1048576000
~~~

⑦\#输入密码，推送完成！



参考博客：

[(27条消息) git命令 fatal: The remote end hung up unexpectedly 出错 解决方法_陈杉菜的博客-CSDN博客](https://blog.csdn.net/qq_44702847/article/details/106638410?ops_request_misc=%7B%22request%5Fid%22%3A%22165883150316780366517763%22%2C%22scm%22%3A%2220140713.130102334..%22%7D&request_id=165883150316780366517763&biz_id=0&utm_medium=distribute.pc_search_result.none-task-blog-2~all~sobaiduend~default-2-106638410-null-null.142^v34^pc_search_v2,185^v2^control&utm_term=fatal%3A the remote end hung up unexpectedly&spm=1018.2226.3001.4187)

https://www.codeprj.com/blog/a7620d1.html

[CSDN编程社区 (smartapps.cn)](https://yebd1h.smartapps.cn/pages/blog/index?_swebFromHost=baiduboxapp&blogId=110876457&_swebfr=1)

https://blog.csdn.net/MAINKEYS/article/details/120219721?ops_request_misc=%257B%2522request%255Fid%2522%253A%2522165883059216781667875386%2522%252C%2522scm%2522%253A%252220140713.130102334.pc%255Fall.%2522%257D&request_id=165883059216781667875386&biz_id=0&utm_medium=distribute.pc_search_result.none-task-blog-2~all~first_rank_ecpm_v1~rank_v31_ecpm-13-120219721-null-null.142^v34^pc_search_v2,185^v2^control&utm_term=fatal%3A%20unable%20to%20access%20https%3A%2F%2Fgithub.com%2Fjoker12-wswq%2Fnotes.git%2F%3A%20OpenSSL%20SSL_read%3A%20Connection%20was%20aborted%2C%20errno%2010053&spm=1018.2226.3001.4187