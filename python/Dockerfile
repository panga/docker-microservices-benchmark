FROM python:2.7.13

EXPOSE 3000

WORKDIR /app

COPY requirements.txt .
RUN pip install --no-cache-dir -r requirements.txt

ADD app.py .

CMD ["python", "app.py"]