FROM python:3.7.6-buster

RUN ["python", "-m", "pip", "install", "keras", "tensorflow"]
RUN ["python", "-c", "from keras.datasets import cifar10;cifar10.load_data()"]

CMD ["bash"]
