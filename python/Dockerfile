FROM python:3.11-alpine

EXPOSE 3000

WORKDIR /app

ADD requirements.txt .
RUN pip install --no-cache-dir -r requirements.txt

ADD app.py .

CMD ["python", "app.py"]