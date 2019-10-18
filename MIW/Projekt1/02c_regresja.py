import numpy as np
import matplotlib.pyplot as plt


a = np.loadtxt('dane7.txt')

x = a[:,[1]]
y = a[:,[0]]

c = np.hstack([x*x*x, x*x, x, np.ones(x.shape)])
v = np.linalg.pinv(c) @ y
cc = np.hstack([1/x, np.ones(x.shape)])
vv = np.linalg.pinv(cc) @ y

plt.plot(x, y, 'ro')
plt.plot(x,v[0]*x*x*x + v[1]*x*x + v[2]*x + v[3],)
plt.plot(x,vv[0]/x + vv[1])
plt.show()

c1 = np.hstack([x*x*x, x*x, x, np.ones(x.shape)])
v1 = np.linalg.pinv(c) @ y
cc1 = np.hstack([1/x, np.ones(x.shape)])
cc1 = np.linalg.pinv(c1) @ y

