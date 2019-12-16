# GWTEntertainment
#### Manually deploy Intellij Idea project on server:
_First option: use `tomcat-launch-files`_.

_Second option:_
1. Verify that GWT module and GWT facet are configured. `File - Project Structure`
2. Open for editing `Run/Debug configuration` on top right corner.
3. Choose `GWT Client`.
4. Uncheck `Super Dev Mode`.
5. Add `Build Web-Exploded artifact` in before launch section.
6. Save. Run configuration.
7. Open `GWT-Client - out - artifacts - GWT...`.
- GWTClient folder:
*.cache.js files;
*.gwt.rpc;
*.nocache.js;
*.other files.
- WEB-INF folder: classes folder, lib folder, web.xml.
- GWTClient.css.
- GWTClient.html.
8. Zip files if it needs. Rename *.zip to *.war.
9. Copy files in server folder.
10. Update `tomcat-launch-files`.

**Final step:** open `/GWTClient/GWTClient.html` or `/GWTClient`.
https://www.tutorialspoint.com/gwt/gwt_deploy_application.htm
