#!/bin/bash
echo"HelloWorld"
sudo cp /var/lib/jenkins/workspace/TreePLE14/TreePLE-Spring/build/libs/treePLE-0.0.1-SNAPSHOT.war /opt/tomcat/webapps/ROOT.war
sudo chown tomcat -R /opt/tomcat/
systemctl start tomcat
systemctl status tomcat
