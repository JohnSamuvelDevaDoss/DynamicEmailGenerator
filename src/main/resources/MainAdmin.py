import smtplib
import ssl
from email.mime.text import MIMEText
from email.mime.multipart import MIMEMultipart

port = 465  # For SSL
password ="Johndeva@1995"

sender_email = "johnoutlook1995@gmail.com"
receiver_email = "johnsamuveld@gmail.com"
message = MIMEMultipart()
message["Subject"] = "multipart test"
message["From"] = sender_email
message["To"] = receiver_email

# Create the plain-text and HTML version of your message
text = ""
html = """\
<html>
  <body>
    <p>Hi,<br>
       How are you?<br> 
       has many great tutorials.
    </p>
      <img src="https://e8f90b8a.ngrok.io/api/getpage"/>
     
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
