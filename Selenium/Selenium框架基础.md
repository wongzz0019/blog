# 元素定位

## 1、代码实现步骤

### 1-1、导包

```python
from selenium import webdriver
```



### 1-2、浏览器驱动对象

```python
Firefox浏览器：driver = webdriver.Firefox() 
Chrome浏览器：driver = webdriver.Chrome() 
Edge浏览器：driver = webdriver.Edge() 
```



### 1-3、打开web页面

```python
driver.get("http://www.xxxxx.com/") 
```



### 1-4、定位元素

```python
driver.find_element(By.ID,"userA").send_keys("admin")
```



### 1-5、等待时间

```python
time.sleep(3)
```



### 1-6、退出浏览器

```python
driver.quit()
```



## 2、元素定位

```python
# 1.id定位
driver.find_element(By.ID, "id")

# 2.name定位  当有多个name属性，始终定位的是第一个
driver.find_element(By.NAME, "name")

# 3.class_name定位
driver.find_element(By.CLASS_NAME, "class_name")

# 4.tag_name定位
driver.find_element(By.TAG_NAME, "tag")

# 5.link_text定位  主要用在定位a标签，全部文本信息
driver.find_element(By.LINK_TEXT, "text")

# 6.partail_link_text定位  主要用在定位a标签，部分文本信息
driver.find_element(By.PARTAIL_LINK_TEXT, "text")

# 7.xpath定位
driver.find_element(By.XPATH, "xpath")
绝对路径：/html/body/div/fifieldset/form/p[1]/input
相对路径：//tag_name或者//* 开头 ;  例如://p[5]/button
//*[]
```





