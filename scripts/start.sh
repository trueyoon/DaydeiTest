#!/usr/bin/env bash

PROJECT_ROOT="/home/ubuntu/app"
JAR_FILE="$PROJECT_ROOT/"

APP_LOG="$PROJECT_ROOT/application.log"
ERROR_LOG="$PROJECT_ROOT/error.log"
DEPLOY_LOG="$PROJECT_ROOT/deploy.log"

TIME_NOW=$(date +%c)

# build 파일 복사
echo "$TIME_NOW > $JAR_FILE 파일 복사" >> $DEPLOY_LOG
cp $PROJECT_ROOT/build/libs/*.jar $JAR_FILE

# jar 파일 실행
echo "$TIME_NOW > $JAR_FILE 파일 실행" >> $DEPLOY_LOG
nohup java -jar -Duser.timezone=Asia/Seoul $JAR_FILE > $APP_LOG 2> $ERROR_LOG &

CURRENT_PID=$(pgrep -f $JAR_FILE)
echo "$TIME_NOW > 실행된 프로세스 아이디 $CURRENT_PID 입니다." >> $DEPLOY_LOG
#
#ABSPATH=$(readlink -f $0)
#ABSDIR=$(dirname $ABSPATH)
#source ${ABSDIR}/profile.sh
#
#REPOSITORY=/home/ubuntu/app/step3
#PROJECT_NAME=freelec-springboot2-webservice
#
#echo "> Build 파일 복사"
#echo "> cp $REPOSITORY/zip/*.jar $REPOSITORY/"
#
#cp $REPOSITORY/zip/*.jar $REPOSITORY/
#
#echo "> 새 어플리케이션 배포"
#JAR_NAME=$(ls -tr $REPOSITORY/*.jar | tail -n 1)
#
#echo "> JAR Name: $JAR_NAME"
#
#echo "> $JAR_NAME 에 실행권한 추가"
#
#chmod +x $JAR_NAME
#
#echo "> $JAR_NAME 실행"
#
#IDLE_PROFILE=$(find_idle_profile)
#
#echo "> $JAR_NAME 를 profile=$IDLE_PROFILE 로 실행합니다."
#nohup java -jar \
#    -Dspring.config.location=classpath:/application.properties,classpath:/application-$IDLE_PROFILE.properties,/home/ubuntu/app/application-oauth.properties,/home/ububtu/app/application-real-db.properties \
#    -Dspring.profiles.active=$IDLE_PROFILE \
#    $JAR_NAME > $REPOSITORY/nohup.out 2>&1 &