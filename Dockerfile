FROM fabric8/java-centos-openjdk11-jre
ADD target/shopmart-backend-0.0.1-SNAPSHOT.jar /home/
CMD ["java","-jar","/home/shopmart-backend-0.0.1-SNAPSHOT.jar"]