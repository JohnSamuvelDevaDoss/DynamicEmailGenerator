import smtplib
import ssl
from email.mime.text import MIMEText
from email.mime.multipart import MIMEMultipart

port = 465  # For SSL
password ="Johndeva@1995"

sender_email = "johnoutlook1995@gmail.com"
receiver_email = "johnsamuveldeva@hotmail.com"
message = MIMEMultipart()
message["Subject"] = "Dynamic Email Testing"
message["From"] = sender_email
message["To"] = receiver_email

# Create the plain-text and HTML version of your message
text = ""
html = """\
<html>
<head>
  <title>Bootstrap Example</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
</head>
<body>
<div class="container">
<div class="row">
    <div class="col-sm-3">
<img src="https://cdn.pixabay.com/photo/2016/04/15/18/05/computer-1331579_960_720.png" style="
    width: 200;
    height: 200;margin-top: 50;
"/>
</div>
    <div class="col-sm-3">
    <img src="https://301fcf04.ngrok.io/api/abc/getpage"/>
    </div>
</div>
</div>
</body>
</html>
"""

# Turn these into plain/html MIMEText objects
part2 = MIMEText(html, "html")

# Add HTML/plain-text parts to MIMEMultipart message
# The email client will try to render the last part first
message.attach(part2)

# Create a secure SSL context

context = ssl.create_default_context()
with smtplib.SMTP("smtp.gmail.com", 587) as server:
    server.ehlo()  # Can be omitted
    server.starttls(context=context)
    server.ehlo()  # Can be omitted
    server.login(sender_email, password)
    server.sendmail(sender_email, receiver_email, message.as_string())
