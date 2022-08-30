### 通用服務元件

為提升開發速度，提供常用元件設計開放設計，

### 目前提供功能

1. 有Key規則之資料集元件
2. 常用有線狀態機元件
3. 門栓排程服務件
4. 代碼服務元件
5. 指令命令元件
6. 排程服務元件
7. 工人池

### WorkLatchService（多工服務）

### 使用場景

需使用多工場景，將所有任務分配於工人，所以任務完成後，工頭才會繼續後續作業

### IQueue 任務

- IOrderKeyQueue(同樣key資料將不會同時執行)
- BlockQueue(堵塞池)，工作項目達到限制範圍內，停止加入項目
- ListQueue(清單池)

### [IWorkPool 工人池](WorkPool)

- CountDownLatchWorkPool(有限工作，手上代辦任務一次取得)
- AlwAysAliveWorkPool(無限工作，持續收到工作，直接到關閉服務，才清除工人池)
