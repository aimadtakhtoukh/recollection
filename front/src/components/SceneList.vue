<template>
    <div>
        <div v-for="scene in scenes" :key="scene.id">
            <div>{{scene.id}}</div>
            <div>{{scene.title}}</div>
        </div>
    </div>
</template>

<script>
    export default {
        name: "SceneList",
        props : {
            scenes : {type : Array, required : true}
        }
    }
</script>

<style scoped>

</style>