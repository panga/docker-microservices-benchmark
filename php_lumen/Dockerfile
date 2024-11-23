FROM php:7.1-fpm-alpine

RUN apk add --no-cache \
    bash \
    git \
    openssh

WORKDIR /var/www

COPY . /var/www

RUN curl -sS https://getcomposer.org/installer | php -- --install-dir=/usr/local/bin --filename=composer
RUN composer install

EXPOSE 3000

ENTRYPOINT ["php", "-S", "0.0.0.0:3000", "-t", "public"]
