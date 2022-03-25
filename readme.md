# 延迟计算

## 开发者

黄江南 毛夏煜

## 功能

### 计算A653延迟





### 计算异步延迟

#### 工作量

| ***\*序号\**** | ***\*默认\*******\*场景路径\****                             | 是否完成 |
| -------------- | ------------------------------------------------------------ | -------- |
| ***\*1\****    | A653端口 → ADN 网络（Switch Application）                    | ==否==   |
| ***\*2\****    | A653端口 → ADN 网络 → A653端口                               | 是   |
| ***\*3\****    | A653端口 → ADN 网络 →LRU的A664端口                           | 是       |
| ***\*4\****    | A653端口 → ADN 网络 → RDIU 转换 → LRU的A429端口              | 是       |
| ***\*5\****    | A653端口 → ADN 网络 → RDIU 转换 → LRU的A825端口              | 是       |
| ***\*6\****    | A653端口 → ADN 网络 → RDIU 转换 → Analog端口                 | 是       |
| ***\*7\****    | LRU的A664端口 → ADN 网络（Switch Application）               | ==否==   |
| ***\*8\****    | LRU的A664端口 → ADN 网络 → LRU的A664端口                     | 是       |
| ***\*9\****    | LRU的A664端口 → ADN 网络 → RDIU 转换 → LRU的A429端口         | 是       |
| ***\*10\****   | LRU的A664端口 → ADN 网络 → RDIU 转换 → LRU的A825端口         | 是       |
| ***\*11\****   | LRU的A664端口 → ADN 网络 → A653端口                          | 是       |
| ***\*12\****   | LRU的A429端口 → RDIU转换 → ADN 网络（Switch Application）    | ==否==   |
| ***\*13\****   | LRU的A429端口 → RDIU 转换 → ADN 网络 → A653端口              | 是       |
| ***\*14\****   | LRU的A429端口 → RDIU 转换 → ADN 网络 → LRU的A664端口         | 是       |
| ***\*15\****   | LRU的A429端口 → RDIU 转换 → ADN 网络 → RDIU 转换 → LRU的A429端口 | 是       |
| ***\*16\****   | LRU的A429端口 → RDIU 转换 → ADN 网络 → RDIU 转换 → LRU的A825端口 | 是       |
| ***\*17\****   | LRU的A429端口 → RDIU 转换 → ADN 网络 → RDIU 转换 → LRU的Analog端口 | ==否==   |
| ***\*18\****   | LRU的A825端口 →RDIU转换→ ADN 网络（Switch Application）      | ==否==   |
| ***\*19\****   | LRU的A825端口 → RDIU 转换 → ADN 网络 → A653端口              | 是       |
| ***\*20\****   | LRU的A825端口 → RDIU 转换 → ADN 网络 → LRU的A664端口         | 是       |
| ***\*21\****   | LRU的A825端口 → RDIU 转换 → ADN 网络 → RDIU 转换 → LRU的A429端口 | 是       |
| ***\*22\****   | LRU的A825端口 → RDIU 转换 → ADN 网络 → RDIU 转换 → LRU的A825端口 | 是       |
| ***\*23\****   | LRU的A825端口 → RDIU 转换 → ADN 网络 → RDIU 转换 → LRU的Analog端口 | 是       |
| ***\*24\****   | LRU的Analog端口 →RDIU转换→ADN 网络（Switch Application）     | ==否==   |
| ***\*25\****   | LRU的Analog端口 → RDIU 转换 → ADN 网络 → A653端口            | 是       |
| ***\*26\****   | LRU的Analog端口 → RDIU 转换 → ADN 网络 → LRU的A664端口       | 是       |
| ***\*27\****   | LRU的Analog端口 → RDIU 转换 → ADN 网络 → RDIU 转换 → LRU的A429端口 | 是       |
| ***\*28\****   | LRU的Analog端口 → RDIU 转换 → ADN 网络 → RDIU 转换 → LRU的A825端口 | 是       |
| ***\*29\****   | LRU的Analog端口 → RDIU 转换 → ADN 网络 → RDIU 转换 → LRU的Analog端口 | 是       |
| ***\*30\****   | ADN 网络（Switch Application）→ ADN 网络（Switch Application） | ==否==   |
| ***\*31\****   | ADN 网络（Switch Application）→ A653端口                     | ==否==   |
| ***\*32\****   | ADN 网络（Switch Application）→ LRU的A664端口                | ==否==   |
| ***\*33\****   | ADN 网络（Switch Application）→ RDIU转换 → LRU的A429端口     | ==否==   |
| ***\*34\****   | ADN 网络（Switch Application）→ RDIU转换 → LRU的A825端口     | ==否==   |

