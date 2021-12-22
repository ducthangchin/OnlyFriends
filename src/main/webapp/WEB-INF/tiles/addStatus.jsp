<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div class="row">

    <div class="col-md-8 col-md-offset-2">

        <div class="panel panel-default">

            <div class="panel-heading">
                <div class="panel-title">Add a Status Update</div>
            </div>



            <%--@elvariable id="statusUpdate" type="com.ducthangchin.model.StatusUpdate"--%>
            <form:form modelAttribute="statusUpdate">


                <div class="errors">
                    <form:errors path="text" />
                </div>
                <div class="form-group">
                    <form:textarea path="text" name="text" rows="10" cols="50"></form:textarea>
                </div>

                <input type="submit" name="submit" value="Add Status" />
            </form:form>




        </div>


    </div>



</div>



<script src="https://cdn.tiny.cloud/1/6gglfac3coistckrhmzd75f8l7k7ijs8l96tvqvlhmh50aro/tinymce/5/tinymce.min.js" referrerpolicy="origin"></script>


<script>
    tinymce.init({
        selector: 'textarea',
        plugins: 'link',
        branding: false,
        forced_root_block: false

    });
</script>