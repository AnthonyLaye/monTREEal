#!/bin/bash

cp /var/lib/jenkins/workspace/TreePLE14/TreePLE-Spring/build/libs/treePLE-0.0.1-SNAPSHOT.war /opt/tomcat/webapps/treePLE.war
systemctl restart tomcat
systemctl status tomcat
