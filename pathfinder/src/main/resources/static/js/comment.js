const routeId = document.getElementById('routeId').value

const commentsContainer = document.getElementById('commentCtnr')

const allComments = []

const displayComments = (comments) => {

}

function asComment(c) {
    let commentHtml = <div id="commentCtnr-${c.commentId}">

        commentHtml += <h4>${c.user} (${c.created})</h4>
        commentHtml += `<p>${c.message}</p>`
        commentHtml += </div>

    return commentHtml
}