<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div class="row">

    <div class="col-md-6 col-md-offset-3">


        <div class="panel panel-default">

            <div class="panel-heading">

                <div class="panel-title">Edit Status</div>

            </div>


            <%--@elvariable id="statusToEdit" type="com.ducthangchin.model.StatusUpdate"--%>
            <form:form modelAttribute="statusToEdit">

                <form:input type="hidden" path="id"/>
                <form:input type="hidden" path="added"/>
                <form:input type="hidden" path="owner"/>
                <form:input type="hidden" path="imgURL"/>


                <div class="error">
                    <span style="color: red;"><form:errors path="text"/></span>
                </div>


                <div class="form-group">
                    <!-- The path attribute must correspond to the set method -->
                    <form:textarea path="text" name="text" row="10" cols="50"></form:textarea>
                </div>



                <input type="submit" name="submit" value="Update" />

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