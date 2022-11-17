FROM node:14.2.0-alpine3.11
    WORKDIR /usr/src/app
    COPY package.json package-lock.json ./
    RUN npm i -g @angular/cli
    
    # Install app dependencies:
    RUN npm i 
    
    COPY . .
    RUN ng build --prod
ENTRYPOINT ["ng","serve","--host","0.0.0.0","--port","4201"]
