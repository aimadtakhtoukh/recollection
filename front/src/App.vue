<template>
  <div id="app">
    <scene-editor v-for="scene in scenes" :key="scene.id" :scene="scene"></scene-editor>
  </div>
</template>

<script>
import SceneService from "./services/sceneService";
import SceneEditor from "./components/SceneEditor";

export default {
  name: 'app',
  components: {
    SceneEditor
  },
  data() {
    return {
      scenes : []
    }
  },
  mounted() {
    SceneService.getAll().then(scenes => this.scenes = scenes);
  }
}
</script>

<style>
</style>
