<template>
  <div class="scene-editor-wrapper">
    <input type="text" v-model="sceneData.title"/>
    <div>
      <v-runtime-template :template="templateData"></v-runtime-template>
    </div>
    <ckeditor :editor="editor" v-model="sceneData.info.template" :config="editorConfig"></ckeditor>
    <div v-for="key in templateVars" :key="key">
      <span>{{key}}</span>
      <input type="text" v-model="sceneData.info.vars[key]"/>
    </div>
    <button @click="saveEditor()">Sauvegarder</button>
  </div>
</template>

<script>
  import ClassicEditor from '@ckeditor/ckeditor5-build-classic';
  import SceneService from "../services/sceneService";
  import VRuntimeTemplate from "v-runtime-template";

  export default {
    name: "SceneEditor",
    components : {
      VRuntimeTemplate
    },
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
    },
    computed : {
      templateVars() {
        return new Set(Array.from(this.sceneData.info.template.matchAll(/{{(.*?)}}/g)).map(match => match[1]))
      },
      templateData() {
        return '<div>' + this.sceneData.info.template.replace(/{{(.*?)}}/g, "{{sceneData.info.vars.$1}}") + "</div>"
      }
    }
  }
</script>

<style scoped lang="scss">
  .scene-editor-wrapper {

  }
</style>