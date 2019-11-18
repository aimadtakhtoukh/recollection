<template>
  <div class="scene-editor-wrapper">
    <h3>{{scene.title}}</h3>
    <input type="text" v-model="sceneData.title"/>
    <div v-html="scene.info.template"></div>
    <ckeditor :editor="editor" v-model="sceneData.info.template" :config="editorConfig"></ckeditor>
    <button @click="saveEditor()">Sauvegarder</button>
  </div>
</template>

<script>
  import ClassicEditor from '@ckeditor/ckeditor5-build-classic';
  import SceneService from "../services/sceneService";

  export default {
    name: "SceneEditor",
    props : {
      scene : {type : Object, required : true}
    },
    data() {
      return {
        sceneData : this.scene,
        editor: ClassicEditor,
        editorConfig: {}
      }
    },
    methods : {
      saveEditor() {
        SceneService.update(this.sceneData)
      }
    }
  }
</script>

<style scoped lang="scss">
  .scene-editor-wrapper {

  }
</style>