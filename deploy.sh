#!/bin/bash

cp /var/lib/jenkins/workspace/TreePLE14/TreePLE-Spring/build/libs/treePLE-0.0.1-SNAPSHOT.war /opt/tomcat/webapps/tree.war
systemctl start tomcat
systemctl status tomcat
