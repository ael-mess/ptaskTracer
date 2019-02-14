#!/bin/bash



#getting root access
prompt=$(sudo -nv 2>&1)
if [ $? -eq 1 ]; then
    echo "root permission required"
    exit 1
fi
LOC=`pwd`
LIB="/usr/local/lib"
ANTLR="antlr-4.7.2-complete.jar"
BATIK="batik-1.10"
W3C="w3c.jar"
VERSION="-1.0"



echo ""
echo -e "\033[0;35m> Downloading Java .jar libs...\033[0m"
#downloading java lib
cd ${LIB}
if ! [[ -f "${ANTLR}" ]]; then
    sudo wget https://www.antlr.org/download/${ANTLR}
    if [ $? -eq 1 ]; then
        echo "antlr-4.7.2.jar not found, please check if a newer version exists"
        exit 1
    fi
    echo -e "\033[0;32mantlr-4.7.2.jar downloaded in ${LIB}\033[0m"
else
    echo -e "\033[0;32m${ANTLR} already exists in ${LIB}\033[0m"
fi
if ! [[ -d "${BATIK}" ]]; then
    sudo wget http://artfiles.org/apache.org/xmlgraphics/batik/binaries/batik-bin-1.10.zip
    if [ $? -eq 1 ]; then
        echo "batik-bin-1.10.zip not found, please check if a newer version exists"
        exit 1
    fi
    echo -e "\033[0;32mbatik-bin-1.10.zip downloaded in ${LIB}\033[0m"
    sudo jar xvf batik-bin-1.10.zip
    if [ $? -eq 1 ]; then
        echo "batik-bin-1.10 not unpacked"
        exit 1
    fi
    echo -e "\033[0;32mbatik-bin-1.10 unpacked in ${LIB}\033[0m"
else
    echo -e "\033[0;32m${BATIK} already exists in ${LIB}\033[0m"
fi
if ! [[ -f "${W3C}" ]]; then
    sudo wget http://www.java2s.com/Code/JarDownload/w3c/${W3C}.zip
    if [ $? -eq 1 ]; then
        echo "${W3C} not found, please check if a newer version exists"
        exit 1
    fi
    echo -e "\033[0;32m${W3C} downloaded in ${LIB}\033[0m"
    sudo jar xvf ${W3C}.zip
    if [ $? -eq 1 ]; then
        echo "${W3C}.zip not unpacked"
        exit 1
    fi
    echo -e "\033[0;32m${W3C} unpacked in ${LIB}\033[0m"
else
    echo -e "\033[0;32m${W3C} already exists in ${LIB}\033[0m"
fi
sudo rm ${LIB}/w3c.jar.zip ${LIB}/batik-bin-1.10.zip
export CLASSPATH="$CLASSPATH:${LIB}/w3c.jar"
export CLASSPATH="$CLASSPATH:${LIB}/antlr-4.7.2-complete.jar"
export CLASSPATH="$CLASSPATH:${LIB}/batik-1.10/lib/batik-all-1.10.jar"
echo "Jar lib exported in CLASSPATH"
echo -e "[\033[0;32mOK\033[0m]"



echo ""
echo -e "\033[0;35m> Instaling LTTng...\033[0m"
#instaling LTTng
#sudo apt-add-repository ppa:lttng/stable-2.10
#apt-get update
#if [ $? -eq 1 ]; then
#    echo "apt repository not updated"
#    exit 1
#fi
#echo -e "\033[0;32mapt repository updated with ppa:lttng/stable-2.10\033[0m"
sudo apt-get install lttng-tools
if [ $? -eq 1 ]; then
    echo "lttng-tools not installed"
    exit 1
fi
echo -e "\033[0;32mlttng-tools installed\033[0m"
sudo apt-get install lttng-modules-dkms
if [ $? -eq 1 ]; then
    echo "lttng-modules-dkms not installed"
    exit 1
fi
echo -e "\033[0;32mlttng-modules-dkms installed\033[0m"
sudo apt-get install liblttng-ust-dev
if [ $? -eq 1 ]; then
    echo "liblttng-ust-dev not installed"
    exit 1
fi
echo -e "\033[0;32mliblttng-ust-dev installed\033[0m"
echo -e "[\033[0;32mOK\033[0m]"



echo ""
echo -e "\033[0;35m> Compiling java ptaskTracer${VERSION} code...\033[0m"
#compiling
cd ${LOC}
cd src/com/parser/
java -jar ${LIB}/antlr-4.7.2-complete.jar -o java *.g4
cd ../../..
if [ $? -eq 1 ]; then
    echo "Java parser and lexer not generated"
    exit 1
fi
echo -e "\033[0;32mJava parser and lexer generated from traceParser.g4 and traceLexer.g4 in src/com/parser/java\033[0m"
sudo mkdir class
javac -d class src/com/main.java src/com/printer/*.java src/com/event/*.java src/com/task/*.java src/com/parser/*.java src/com/parser/java/*.java
if [ $? -eq 1 ]; then
    echo "Java class not generated"
    exit 1
fi
echo -e "\033[0;32mJava class generated in ./class\033[0m"
cd class
jar cvf ptaskTracer${VERSION}.jar *
if [ $? -eq 1 ]; then
    echo "Jar file not generated"
    exit 1
fi
echo -e "\033[0;32mJar file generated in ./class\033[0m"
jar uvfm ptaskTracer${VERSION}.jar ../src/META-INF/MANIFEST.MF
if [ $? -eq 1 ]; then
    echo "Jar manifest file not updated"
    exit 1
fi
echo -e "\033[0;32mJar manifest file updated\033[0m"
sudo cp ptaskTracer${VERSION}.jar ${LIB}
cd ..
sudo cp ptaskTracer.sh /usr/local/bin/ptaskTracer
type ptaskTracer
if [ $? -eq 1 ]; then
    echo "Application not copied to /usr/local/bin"
    exit 1
fi
echo -e "[\033[0;32mOK\033[0m]"



echo ""
echo -e "\033[0;35m> Cleaning...\033[0m"
sudo rm -r class
echo -e "\033[0;32mClass directory removed\033[0m"
sudo rm src/com/parser/java/*
echo -e "\033[0;32mAntlr java files removed\033[0m"
echo -e "[\033[0;32mOK\033[0m]"
