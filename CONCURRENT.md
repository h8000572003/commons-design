### 多工設計模型

### 目前提供功能

1. 有Key規則之資料集元件
2. 常用有線狀態機元件
3. 門栓排程服務件
4. 代碼服務元件
5. 指令命令元件
6. 排程服務元件
7. 工人池



### WorkLatchService 多工服務


### IQUEUE 任務隊伍

- IOrderKeyQueue(同樣key資料將不會同時執行)
- BlockQueue(堵塞池)，工作項目達到限制範圍內，停止加入項目
- ListQueue(清單池)：順序Ｑueue

### IWorkPool 工人池

### CountDownLatchWorkPool
用途：一次

- AlwAysAliveWorkPool(無限工作，持續收到工作，直接到關閉服務，才清除工人池)
