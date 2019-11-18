import axios from "axios"

const client = axios.create({
  baseURL : "http://localhost:9000/scene"
})

const SceneService = {
  getAll : () => client.get("/all").then(result => result.data).catch(() => []),
  update : (update) => client.post("/update", update)
};

export default SceneService;