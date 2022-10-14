<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>Hello WS</title>
    <link href="/webjars/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <script src="/webjars/jquery/jquery.min.js"></script>
    <script src="/webjars/sockjs-client/sockjs.min.js"></script>
    <script src="/webjars/stomp-websocket/stomp.min.js"></script>
    <script src="/scripts.js"></script>
</head>

<body>
    <div class="container" style="margin-top: 50px">
        <div class="row">
            <div class="col-md-12">
                <form class="form-inline">
                    <div class="form-group">
                        <label for="message">Message</label>
                        <input type="text" id="message" class="form-control" placeholder="Enter your message here...">
                    </div>
                    <button id="send" class="btn btn-default" type="button">Send</button>
                </form>
            </div>
        </div>
        <div class="row">
            <div class="col-md-12">
                <form class="form-inline">
                    <div class="form-group">
                        <label for="private-message">Private Message</label>
                        <input type="text" id="private-message" class="form-control"
                            placeholder="Enter your message here...">
                    </div>
                    <button id="send-private" class="btn btn-default" type="button">Send Private Message</button>
                </form>
            </div>
        </div>
        <div class="row">
            <div class="col-md-12">
                <table id="message-history" class="table table-striped">
                    <thead>
                        <tr>
                            <th>Messages
                                <span id="notifications" style="
                                            color: white;
                                            background-color: red;
                                            padding-left: 15px;
                                            padding-right: 15px;">
                                </span>
                            </th>
                        </tr>
                    </thead>
                    <tbody id="messages">
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</body>

</html>