FROM node:20-alpine

# Set working directory inside the container
WORKDIR /next-app

# Install dependencies
COPY package.json ./
COPY package-lock.json ./

RUN npm install

# Copy all application files to the container
COPY . .

# Build the Next.js application
RUN npm run build

# Expose port 3000
EXPOSE 3000

# Start the Next.js application
CMD ["npm", "start"]
