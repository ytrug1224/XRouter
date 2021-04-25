
计划模仿ARouter，实现基于APT的Route简易工具；

原理讲解：
核心思想是 在processor 中进行标签业务处理：

1. 自定义 @ServiceImpl 标签，然后在processor中进行处理；

```java
    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        if (roundEnvironment.processingOver() && !set.isEmpty()) {
            return false;
        }
        if (set.isEmpty()) {
            logi(TAG + "annotations is empty,return");
            return false;
        }
        
        mServiceImplMap.clear();
        scanClass(set, roundEnvironment);
        
        if (mServiceImplMap.isEmpty()) {
            loge(TAG + " find no class impl for @ServiceImpl");
            return false;
        }

        printScanned();
        generateJavaFile();
        return false;
    }
```
核心代码scanClass：
找到 所有@ServiceImpl修饰的类
```java
    Set<? extends Element> elements = roundEnvironment.getElementsAnnotatedWith(ServiceImpl.class);
```

生成相关类：
```java
    private void generateJavaFile() {
        try {
            JavaFile javaFile = JavaFile.builder(APT_PACKAGE_NAME, createType())
                    .addFileComment("$S", "Generated code from " + APT_PACKAGE_NAME + "." + APT_FILE_NAME + " Do not modify!")
                    .build();
            javaFile.writeTo(mFiler);
        } catch (IOException e) {
            loge(e);
        }
    }
```
    

如果对你有用，请star～～～
|微信         | 支付宝           | 
| ------------- |:-------------:| 
| ![](./wechat.png)      | ![](./alipay.png) |


