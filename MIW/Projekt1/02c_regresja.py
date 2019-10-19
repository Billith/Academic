import numpy as np
import matplotlib.pyplot as plt


a = np.loadtxt('dane7.txt')
tren, test = np.array_split(a, 2)

x = tren[:,[0]]
y = tren[:,[1]]

c = np.hstack([x*x*x, x*x, x, np.ones(x.shape)])
v = np.linalg.pinv(c) @ y
cc = np.hstack([1/x, np.ones(x.shape)])
vv = np.linalg.pinv(cc) @ y

plt.plot(x, y, 'ro')
plt.plot(x, v[0]*x*x*x + v[1]*x*x + v[2]*x + v[3])
plt.plot(x, vv[0]/x + vv[1])
plt.show()

