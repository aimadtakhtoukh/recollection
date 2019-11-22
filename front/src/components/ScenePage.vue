<template>
    <div class="scene">
        <scene-list
                :scenes="scenes"
                class="scene-list">
        </scene-list>
        <div class="scene-editor-wrapper">
            <scene-editor
                    v-for="scene in scenes"
                    :key="scene.id"
                    :scene="scene"
                    class="scene-editor">
            </scene-editor>
        </div>
    </div>
</template>

<script>
    import SceneService from "../services/sceneService";
    import SceneEditor from "./SceneEditor";
    import SceneList from "./SceneList";

    export default {
        name: "ScenePage",
        components: {
            SceneList,
            SceneEditor
        },
        data() {
            return {
                scenes : []
            }
        },
        mounted() {SceneService.getAll().then(scenes => this.scenes = scenes);}
    }
</script>

<style scoped lang="scss">
    .scene {
        display: flex;
        flex-direction: row;

        .scene-list {
            flex-direction: column;
            flex-grow : 1
        }

        .scene-editor-wrapper {
            display: flex;
            flex-direction: column;
            flex-grow: 4;
            .scene-editor {
                flex-direction : column;
                width: 100%;
            }
        }
    }
</style>