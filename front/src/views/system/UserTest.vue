<template>
  <div>
    {{text}}
  </div>
</template>
<script>
  import { getAction } from '@/api/manage'
  export default {
    data () {
      return {
        text: "initial"
      }
    },
    created(){
      getAction('/hi/hi').then((res) => {
          console.log("------------")
          console.log(res)
          this.text= res.message
      })
    }
  }
</script>