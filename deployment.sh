#!/bin/bash
#change directories to TreePLE-Spring/build/libs
cd TreePLE-Spring/build/libs
#copy the .WAR file to tomcat
sudo cp treePLE-0.0.1-SNAPSHOT.war /opt/tomcat/webapps/ROOT.war
#allow Tomcat to write
sudo chown tomcat -R /opt/tomcat/

#start the tomcat 
sudo systemctl start tomcat
#show status 
sudo systemctl status tomcat
