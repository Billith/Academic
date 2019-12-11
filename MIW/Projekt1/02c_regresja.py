import numpy as np
import matplotlib.pyplot as plt


a = np.loadtxt('dane7.txt')
tren, test = np.array_split(a, 2)

x = a[:,[0]]
y = a[:,[1]]

c = np.hstack([x*x*x*x*x, x*x*x*x, x*x*x, x*x, x, np.ones(x.shape)])
v = np.linalg.pinv(c) @ y

cc = np.hstack([x*x, np.ones(x.shape)])
vv = np.linalg.pinv(cc) @ y

e = sum((y - (v[0]/x + v[1])) * (y - (v[0]/x + v[1])))
ee = sum((y - (vv[0]/x + vv[1])) * (y - (vv[0]/x + vv[1])))
print(e, ee)

plt.plot(x, y, 'ro')
plt.plot(x, v[0]*x*x*x*x*x + v[1]*x*x*x*x + v[2]*x*x*x + v[3]*x*x + v[4]*x + v[5])
plt.plot(x, vv[0]*x + vv[1])
plt.show()
