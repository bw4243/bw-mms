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
    mounted: function (){
      getAction('/hi/hi').then((res) => {
          console.log("------------")
          console.log(res.message)
          this.text= res.message;
      })
    }
  }
</script>