

dependencies {
    compile 'org.litepal.android:core:1.5.1'
}
```
application 初始化   LitePal.initialize(this);
#### 2. 配置litepal.xml
在项目的assets目录下面新建一个litepal.xml文件，内容如下：

 * <dbname>是数据库的名字
 * <version>是数据库的版本号
 * <list>是数据库的映射模型（数据库表）
 * <mapping>是数据库的映射模型的地址（数据库表结构）
 
#### 3. 配置LitePalApplication
在AndroidManifest.xml中配置LitePalApplication，如下：
``` xml
<manifest>
    <application
        android:name="org.litepal.LitePalApplication"
        ...
    >
        ...
    </application>
</manifest>
```

如果已经有自己的Application，那么久继承一下就好了，如下：
```java
public class MyOwnApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        LitePal.initialize(this);
    }
    ...
}
```


#### 1. LitePal的建表
根据对象关系映射模式的理念，每一张表都应该对应一个模型(Model)，建表先要新建一个模型类，新建一个DEST类，如下：
``` java
public class DEST extends DataSupport {
    private String destId;//目的地ID
    private String cnName;//中文名
    private String enName;//英文名
    private String parentId;
    private String childrenId;
    private long updateTime;

    // 自动生成get、set方法  
}
```
LitePal的映射规则是非常轻量级的，不像一些其它的数据库框架，需要为每个模型类单独配置一个映射关系的XML，LitePal的所有映射都是自动完成的。根据LitePal的数据类型支持，可以进行对象关系映射的数据类型一共有8种，int、short、long、float、double、boolean、String和Date。只要是声明成这8种数据类型的字段都会被自动映射到数据库表中，并不需要进行任何额外的配置。


### 注意
> 只有private修饰的字段才会被映射到数据库表中，即如果有某一个字段不想映射的话，就设置为public、protected或者default修饰符就可以了。
	
* 建立好Model后，我们就把他配置到映射列表中，即编辑assest目录下的litepal.xml文件，在<list>标签中加入DEST类的声明，这里要注意，要类的完整类名。

``` xml
<?xml version="1.0" encoding="utf-8"?>  
<litepal>  
    <dbname value="white" ></dbname>  

    <version value="1" ></version>  

    <list>  
        <mapping class="com.whitelaning.example.litepal.DEST"></mapping>
    </list>  
</litepal>
```
到这里，就完成了LitePal数据库的配置。

LitePal的升级表

1.添加新表
首先创建一个新的模型类，然后把它设置到litepal.xml中，如下：

``` xml
<?xml version="1.0" encoding="utf-8"?>  
<litepal>  
    <dbname value="white" ></dbname>  

    <version value="1" ></version>  

    <list>  
        <mapping class="com.whitelaning.example.litepal.DEST"></mapping>
        <mapping class="com.whitelaning.example.litepal.SHOPPING"></mapping>
    </list>  
</litepal>
```
然后，把litepal.xml中的version的值加一即可，如下：
``` xml
<?xml version="1.0" encoding="utf-8"?>  
<litepal>  
    <dbname value="white" ></dbname>  

    <version value="2" ></version>  

    <list>  
        <mapping class="com.whitelaning.example.litepal.DEST"></mapping>
        <mapping class="com.whitelaning.example.litepal.SHOPPING"></mapping>
    </list>  
</litepal>
```

2.旧表添加新列
首先在需要升级的模型类中添加新的private修饰的字段，如下：
``` java
public class DEST extends DataSupport {
    private String destId;//目的地ID
    private String cnName;//中文名
    private String enName;//英文名
    private String parentId;
    private String childrenId;
    private long updateTime;
    private String imagePath;//（新增加的列）

    // 自动生成get、set方法  
}
```

然后再把litepal.xml中的version的值加一即可，如下：
``` xml
<?xml version="1.0" encoding="utf-8"?>  
<litepal>  
    <dbname value="white" ></dbname>  

    <version value="3" ></version>  

    <list>  
        <mapping class="com.whitelaning.example.litepal.DEST"></mapping>
        <mapping class="com.whitelaning.example.litepal.SHOPPING"></mapping>
    </list>  
</litepal>
```

### LitePal的存储操作

* LitePal要存储数据，首先模型类要继承DataSupport，即：

``` java
public class DEST extends DataSupport {

}
```

继承了DataSupport类之后，这些实体类就拥有了进行CRUD操作的能力。

#### 3. 存储操作：

``` java
Album album = new Album();
album.setName("album");
album.setPrice(10.99f);
album.setCover(getCoverImageBytes());
album.save();
Song song1 = new Song();
song1.setName("song1");
song1.setDuration(320);
song1.setAlbum(album);
song1.save();
Song song2 = new Song();
song2.setName("song2");
song2.setDuration(356);
song2.setAlbum(album);
song2.save();
```
并且save()操作是有返回值的，所以可以这样：
``` java
if (mDest.save()) {  
    Toast.makeText(context, "存储成功", Toast.LENGTH_SHORT).show();  
} else {  
    Toast.makeText(context, "存储失败", Toast.LENGTH_SHORT).show();  
}
```
#### 4. LitePal的修改操作


如果想把DEST表中id为4的destId改为"1"，可以这样写：
``` java
ContentValues values = new ContentValues();  
values.put("destId", "1");  
DataSupport.update(DEST.class, values, 4);  
```
//或者用下面这种方法
``` java
DEST updateNews = new DEST();  
updateNews.setDestId("1");  
updateNews.update(4);
```
如果想把DEST表中所有destId为"1"的改为"2"可以这样写：
``` java
ContentValues values = new ContentValues();  
values.put("destId", "2");  
DataSupport.updateAll(DEST.class, values, "destId = ?", "1");  
```
//或者用下面这种方法
``` java
DEST updateNews = new DEST();  
updateNews.setdestId("2");  
updateNews.updateAll("destId = ?", "1");
```
#### 5. LitePal的删除操作
比如说我们想删除DEST表中id为2的记录，就可以这样写
``` java
DataSupport.delete(Song.class, 2);
```
想把DEST表中destId为“1”的所有数据删除，就可以这样写：
``` java
DataSupport.deleteAll(DEST.class, "destId = ? ", "1");
```
如果我们想把DEST表中所有的数据全部删除掉，就可以这样写：
``` java
DataSupport.deleteAll(DEST.class);
```

#### 6. LitePal的查询操作
LitePal的查询操作

查询DEST表中id为1的这条记录，使用LitePal就可以这样写：
``` java
DEST mDest = DataSupport.find(DEST.class, 1);
```
想要获取DEST表中的第一条数据，只需要这样写：
``` java
DEST firstDest = DataSupport.findFirst(DEST.class);
```
想要获取News表中的最后一条数据，只需要这样写：
``` java
DEST lastDest = DataSupport.findLast(DEST.class);
```
想把DEST表中id为1、3、5、7的数据都查出来，只需要这样写：
``` java
List<DEST> mDestList = DataSupport.findAll(DEST.class, 1, 3, 5, 7);
```
查询所有数据，只需要这样写：
``` java
List<DEST> mDestList = DataSupport.findAll(DEST.class);
```
想查询DEST表中所有父类id为"1"的数据，就可以这样写：
``` java
List<DEST> mDestList = DataSupport.where("parentId = ?", "1").find(DEST.class);
```
许你并不需要那么多的数据，而是只要cnName和enName这两列数据。那么也很简单，我们只要再增加一个连缀就行了，如下所示：
``` java
List<DEST> mDestList = DataSupport.select("cnName", "enName").where("parentId = ?", "1").find(DEST.class);
```
也许你还要数据按照添加的时间倒序排列，那么可以这样：
``` java
List<DEST> mDestList = DataSupport.select("cnName", "enName").where("parentId = ?", "1").order("updateTime desc").find(DEST.class);
```
数据太多了，其实你只要前10行就行了，那么可以这样：
``` java
List<DEST> mDestList = DataSupport.select("cnName", "enName").where("parentId = ?", "1").order("updateTime desc").limit(10).find(DEST.class);
```
如果我们想进行分页展示，那么翻页了，怎么办？可以添加一个偏移量就好了，这样：
``` java
List<DEST> mDestList = DataSupport.select("cnName", "enName").where("parentId = ?", "1").order("updateTime desc").limit(10).offset(10).find(DEST.class);
```


LitePal的聚合函数

count()
如果想统计行数，那么就可以调用count()即可：
```java
int result = DataSupport.count(DEST.class);
```

如果想统计parentId为"1"的数据的行数，那么可以这样：
```java
int result = DataSupport.where("parentId = ?", "1").count(DEST.class);
```
sum()
如果想统计一下DEST表中，所有updateTime的和（虽然毫无用处....），那么可以这样：
```java
long result = DataSupport.sum(DEST.class, "updateTime", long.class);
```
