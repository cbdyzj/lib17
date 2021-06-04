<template>
  <AModal
      :title="title"
      :visible="visible"
      :confirm-loading="confirmLoading"
      @ok="handleOk"
      @cancel="handleCancel"
  >
    <div class="modal-form">
      Here should be a form!
    </div>
  </AModal>
</template>
<script>
export default {
  props: {
    title: {
      type: String,
      default() {
        return ''
      }
    },
    visible: {
      type: Boolean,
      default() {
        return false
      }
    },
    confirmLoading: {
      type: Boolean,
      default() {
        return false
      }
    }
  },
  methods: {
    handleOk(ev) {
      this.$emit('ok')
    },
    handleCancel(ev) {
      this.$emit('cancel')
    },
  },
}
</script>

<style scoped>
.modal-form {
  text-align: center;
  font-size: 20px;
}
</style>