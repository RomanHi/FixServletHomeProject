<!DOCTYPE html>

<html lang="en">
<title>Json/Xml storage</title>
<body>
<link rel="stylesheet" type="text/css" href="/css/table.css">
<div class="limiter">
    <h5>u token = ${token}</h5>
    <div class="container-table100">
        <div class="wrap-table100">
            <div class="table100 ver1 m-b-110">
                <div class="table100-head">
                    <table>
                        <thead>
                        <tr class="row100 head">
                            <th class="cell100 column0">File Id</th>
                            <th class="cell100 column1">File name</th>
                            <th class="cell100 column2">Type</th>
                            <th class="cell100 column3">Date</th>
                            <th class="cell100 column4">Get File</th>
                        </tr>
                        </thead>
                    </table>
                </div>

                <div class="table100-body js-pscroll">
                    <table>
                        <tbody>
                        <#list files as file>
                        <tr class="row100 body">
                            <td class="cell100 column0">${file.fileId}</td>
                            <td class="cell100 column1">${file.fileName}</td>
                            <td class="cell100 column2">${file.type}</td>
                            <td class="cell100 column3">${file.createDate!}</td>
                            <td class="cell100 column4"><a href="/profile/${file.userId}/file/${file.fileId}">File</a>
                            </td>
                        </tr>
                        </#list>
                        </tbody>
                    </table>
                </div>
            </div>

</body>

</html>