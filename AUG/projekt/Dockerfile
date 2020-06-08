# sudo docker run --rm -it -v `pwd`:/data antlr
FROM openjdk

RUN yum update -y && yum install -y wget
WORKDIR /root
RUN wget https://www.antlr.org/download/antlr-4.8-complete.jar
RUN export CLASSPATH=".:/usr/local/lib/antlr-4.8-complete.jar:$CLASSPATH"
RUN echo "alias antlr4='java -jar /root/antlr-4.8-complete.jar'" >> /root/.bashrc
RUN echo "alias grun='java org.antlr.v4.gui.TestRig'" >> /root/.bashrc

ENTRYPOINT bash
