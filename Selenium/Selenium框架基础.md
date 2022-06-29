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





