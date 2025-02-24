# Use an official MySQL image as a base
FROM mysql:8.4.2

# Set the environment variables
ENV MYSQL_ROOT_PASSWORD=root
ENV MYSQL_DATABASE=publicsoft-db
ENV MYSQL_USER=admin
ENV MYSQL_PASSWORD=P@ssw0rd

# Expose the MySQL port
EXPOSE 3306

# Copy init.sql to create the db
COPY init.sql /docker-entrypoint-initdb.d/

# Run the command to start MySQL when the container starts
CMD ["mysqld"]
